'use strict';

/**
 * @ngdoc function
 * @name saveTheTreesApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the saveTheTreesApp
 */
angular.module('saveTheTreesApp')
  .controller('AboutCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
