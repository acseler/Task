  module.exports = function(config){
  config.set({

    basePath : '../',

    files : [
      'TaskManager/scripts/jquery-1.12.1.min.js',
      'TaskManager/scripts/angular.js',
      'TaskManager/scripts/angular-mocks.js',
      'TaskManager/scripts/bootstrap-datepicker.js',
      'TaskManager/scripts/bootstrap.min.js',
      'TaskManager/scripts/taskManager.js',
      'TaskManager/scripts/app.js',
      'test/unit/*.js'
    ],

    autoWatch : true,

    frameworks: ['jasmine'],

    browsers : ['Chrome'],

    plugins : [
            'karma-chrome-launcher',
            'karma-jasmine',
            'karma-jasmine-jquery'
            ],

    junitReporter : {
      outputFile: 'test_out/unit.xml',
      suite: 'unit'
    }

  });
};