
'use strict';

angular.module('myApp.courseinfo', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/courseinfo/:coursename', {
    templateUrl: 'page_courseinfo/courseinfo.html',
    controller: 'CourseInfoController'
  }).when('/editcourse/:coursename', {
    templateUrl: 'page_createcourse/createcourse.html',
    controller: 'EditCourseController'
  }).otherwise('/mycourses');
}]);

