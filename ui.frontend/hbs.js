const handlebars = require('handlebars');
const layouts = require('handlebars-layouts');
const fs = require('fs');

const getfileNamesFromFolder = function (dir, test) {
  let results = [];
  
  fs.readdirSync(dir).forEach((file) => {
    file = dir + '/' + file;
    const stat = fs.statSync(file);
    
    if (stat && stat.isDirectory()) {
      results = results.concat(getfileNamesFromFolder(file))
    } else results.push(file);
  });

 // if (test) { console.log('files before return: ', results) }
  return results.map((element) => element.indexOf('ui.frontend') > -1 ? element.split('ui.frontend')[1] : element);
};


// Compile components and copy to dist/pages
let components = [];

const compFiles = getfileNamesFromFolder(__dirname + '/src/rmit/partials/components', true);

compFiles.filter(function (value) {
  return value.indexOf('.hbs') > -1;
});



for (let n = 0; n < compFiles.length; n++) {
 let compFilePath = __dirname + compFiles[n];
  fs.readFile(compFilePath, 'utf8', (err, data) => {
    if (err) throw err;
      components[n] = handlebars.compile(data);

      let compOutputPath = compFilePath.replace('/src/rmit/partials/components', '/dist/rmit/pages').replace('.hbs', '.html');
     console.log(compOutputPath);
      fs.writeFile(compOutputPath, components[n](), (err) => {})
  });
}




function ensureExists(path, mask, cb) {
  if (typeof mask == 'function') { // allow the `mask` parameter to be optional
    cb = mask;
    mask = 0777;
  }
  fs.mkdir(path, mask, function(err) {
    if (err) {
      if (err.code == 'EEXIST') cb(null); // ignore the error if the folder already exists
      else cb(err); // something else went wrong
    } else cb(null); // successfully created folder
  });
}




const comps = getfileNamesFromFolder(__dirname + '/src/rmit/partials/components');


// Register helpers
layouts.register(handlebars);


// Register partials
comps.filter(function (value) {
  return value.indexOf('.hbs') > -1;
});


for (let i = 0; i < comps.length; i++) {
  handlebars.registerPartial(comps[i].split('.')[0].split('/src/rmit/partials/components')[1], fs.readFileSync(__dirname + comps[i], 'utf8'));
}



const partials = getfileNamesFromFolder(__dirname + '/src/rmit/partials');

// Register helpers
layouts.register(handlebars);

// Register partials
partials.filter(function (value) {
  return value.indexOf('.hbs') > -1;
});
for (let i = 0; i < partials.length; i++) {
  handlebars.registerPartial(partials[i].split('.')[0].split('/src/rmit/partials/')[1], fs.readFileSync(__dirname + partials[i], 'utf8'));
}



const files = getfileNamesFromFolder(__dirname + '/src/rmit/pages', true);


// Compile template
let template = [];
// console.log(files);

files.filter(function (value) {
  return value.indexOf('.hbs') > -1;
});
for (let j = 0; j < files.length; j++) {
  let filePath = __dirname + files[j];
  fs.readFile(filePath, 'utf8', (err, data) => {
    if (err) throw err;
    
    template[j] = handlebars.compile(data);
    let outputPath = filePath.replace('/src/rmit/pages/', '/dist/rmit/pages/').replace('.hbs', '.html');
  //  console.log(outputPath);
    let fileFolder = outputPath.split('/').slice(0, -1).join('/');
	  
    
    ensureExists(fileFolder, function(err) {
        if (!err) {
          fs.writeFile(outputPath, template[j](), (err) => {})
        } else {
          console.log(err)
        }
    });
  });
}



