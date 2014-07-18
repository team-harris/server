'use strict';

/**
 * @ngdoc overview
 * @name EcoCisco
 * @description #
 *
 * Main module of the application.
 */
angular
  .module('EcoCisco', [])

  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
