var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  console.log(req.params);
  res.render('index.html', { title: 'Welcome to MEX RDF to LaTeX Converter!' });
});

/* POST */
router.post('/', function(req, res, next) {
  console.log(req.params);
  // res.render('index.html', { title: '' });
});



module.exports = router;
