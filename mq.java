const express = require('express');
const passport = require('passport');
const SteamStrategy = require('passport-steam').Strategy;
const app = express();

passport.use(new SteamStrategy({
    returnURL: 'https://yourdomain.com/auth/steam/callback',
    realm: 'https://yourdomain.com/',
    apiKey: 'DCC2C2C5C66A1BFD9F141D31663AD1D1'
  },
  (identifier, profile, done) => {
    // Save profile.steamid, profile.displayName, etc. to your database
    return done(null, profile);
  }
));

app.get('/auth/steam', passport.authenticate('steam'));
app.get('/auth/steam/callback', passport.authenticate('steam', { failureRedirect: '/' }), (req, res) => {
  res.redirect('/'); // Redirect to main page with user info
});

app.listen(3000);
