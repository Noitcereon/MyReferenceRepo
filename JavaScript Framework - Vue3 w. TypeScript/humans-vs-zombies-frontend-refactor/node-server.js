const express = require('express');
const serveStatic = require("serve-static")
const path = require('path');
app = express();
app.use(serveStatic(path.join(__dirname, 'dist')));
const port = process.env.PORT || 5000;
app.listen(port);
app.get('/*', function (req, res) {
    console.log(`${req.url} was requested. Falling back to landing page.`);
    res.redirect("/");
})
console.log("Server started on port: ", port)