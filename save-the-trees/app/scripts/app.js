'use strict';

/**
 * @ngdoc overview
 * @name EcoCisco
 * @description #
 *
 * Main module of the application.
 */
(function() {
  var app = angular.module('EcoCisco', []); 

  app.controller("GlobalController", function($scope) {
    $scope.state = {
      isLoggedIn: false
    };

    $scope.changeLoginState = function() {
      this.state.isLoggedIn = true;
    };

  });

  app.config(function ($routeProvider) {
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
  })
  
  app.controller('LoginController', function($scope) {

      $scope.credentials = {
        username: '',
        password: ''
      };

      $scope.login = function() {
        this.changeLoginState();
      };

  });

  app.controller('SideNavController', function($scope) {

    $scope.buttons = [{
      label: "My EcoCisco", 
      class: "fa fa-dashboard",
      selected: true
    }, {
      label: "My EcoStanding",
      class: "fa fa-table",
      selected: false
    }, {
      label: "My EcoEnergy",
      class: "fa fa-bar-chart-o",
      selected: false
    }, {
      label: "About",
      class: "fa fa-edit",
      selected: false
    }];

    function returnSelectedButton(array) {
      array.forEach(function(element) {
        if (element.selected) {
          return element;
        }
      });
      return null;
    }

    $scope.isSelected = function(index) {
      return (this.buttons[index].selected);
    }

    $scope.toggle = function(index) {
      var prevSelectedElem = returnSelectedButton(this.buttons);
      if (prevSelectedElem) {
        prevSelectedElem.selected = false;
      }
      this.buttons[index].selected = true;
    }

  });

})();