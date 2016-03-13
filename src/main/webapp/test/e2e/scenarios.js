'use strict';

/* http://docs.angularjs.org/guide/dev_guide.e2e-testing */

describe('angularjs task manager', function() {
  it('should be SIMPLE TODO LISTS title', function() {
    browser.get('http://localhost:8080/');

    expect(browser.getTitle()).toEqual('SIMPLE TODO LISTS');
  });

  //it('should ')

});