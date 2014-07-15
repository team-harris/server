'use strict';

/**
 * @ngdoc function
 * @name saveTheTreesApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the saveTheTreesApp
 */
angular.module('saveTheTreesApp')
  .controller('MainCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
