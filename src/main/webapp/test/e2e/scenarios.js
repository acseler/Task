'use strict';

/* http://docs.angularjs.org/guide/dev_guide.e2e-testing */

describe('angularjs task manager', function() {
  it('should be SIMPLE TODO LISTS title', function() {
    browser.get('http://localhost:8080/');

    expect(browser.getTitle()).toEqual('SIMPLE TODO LISTS');
  });

  it('login test', function() {
    browser.get('http://localhost:8080/');
    expect($('#RegistrationForm').isDisplayed()).toBe(false);
    expect($('#TaskData').isDisplayed()).toBe(false);
    $('#login').sendKeys('ivan');
    $('#password').sendKeys('secret');
    element(by.buttonText('Log in')).click();
    expect($('#RegistrationForm').isDisplayed()).toBe(false);
    expect($('#TaskData').isDisplayed()).toBe(true);
  });

  it('tasks test', function() {
    browser.get('http://localhost:8080/');
    var list = element.all(by.repeater('p in projects'));
    expect(list.count()).toBe(3);
    expect(element(by.className('col-xs-8')).getText()).toBe('Create project');
    expect(element(by.model('taskName')).getAttribute('value')).toBe('');
    var listOfTasks = element.all(by.repeater('task in p.tasks'));
    expect(listOfTasks.count()).toBe(4)
    element(by.model('taskName')).sendKeys('new task');
    element(by.buttonText('Add task')).click();
    expect(listOfTasks.count()).toBe(5);
    var secondTaskCheckBox = element.all(by.css('input[type=checkbox]')).get(1);
    expect(secondTaskCheckBox.isSelected()).toBe(true);
    var deadline = element.all(by.className('dead-line')).get(1);
    expect(deadline.isDisplayed()).toBe(true);
  });
});