angular.module('myApp.createcourse').controller('CreateCourseController', [
	'$scope',
	'$filter',
	'$modal',
	'createcourse',
	'userService',
	'initCourseService',
	'setFieldsService',
	function ($scope, $filter, $modal, createcourse, userService, initCourseService, setFieldsService) {
		$scope.isEdited = false;
		$scope.header = 'Create';
		$scope.disabled = 'courseForm.name.$dirty && courseForm.name.$invalid || courseForm.description.$dirty && courseForm.description.$invalid || courseForm.audience.$dirty && courseForm.audience.$invalid || courseForm.participantsNumber.$dirty && courseForm.participantsNumber.$invalid || courseForm.place.$dirty && courseForm.place.$invalid';
		$scope.disabled2 = 'courseForm.$invalid';

		$scope.categoryToggle = false;

		initCourseService($scope);

		$scope.courseInfo.picture = {
			data: '',
			link: '',
			name: '',
		};

		$scope.saveData = function () {
			if ($scope.courseInfo.isRepeating) {
				$scope.courseInfo.repeatOn = angular.copy($scope.temp.repeatOn);
				$scope.courseInfo.startsOn = $scope.temp.startsOn;
				$scope.courseInfo.startsOn = $filter('date')($scope.courseInfo.startsOn, 'yyyy-MM-dd HH:mm');
				$scope.courseInfo.lessonsNumber = $scope.temp.lessonsNumber;
				for (var i = 0; i < $scope.courseInfo.lessonsNumber; i++) {
					$scope.courseInfo.places.push($scope.temp.place);
				}
			}
			else {
				$scope.courseInfo.dateTime = angular.copy($scope.temp.tempDates); //TEMP
				for (var i = 0; i < $scope.courseInfo.dateTime.length; i++) {
					$scope.courseInfo.dateTime[i] = $filter('date')($scope.courseInfo.dateTime[i], 'yyyy-MM-dd HH:mm');
					$scope.courseInfo.places.push($scope.temp.place);
				}
			}

			console.log($scope.courseInfo);
			createcourse.createCourse($scope.courseInfo); //! ? some then() with alert...?
		};

		$scope.setCategory = function (id) {
			setFieldsService.setCategory($scope, id);
		};

		$scope.setType = function (type) {
			setFieldsService.setType($scope, type);
		};

		$scope.setLanguage = function (lang) {
			setFieldsService.setLanguage($scope, lang);
		};

		$scope.setCoach = function(coach) {
			setFieldsService.setCoach($scope, coach);
		};

		$scope.setAdminAsCoach = function() {
			setFieldsService.setAdminAsCoach($scope);
		};

		$scope.addCoach = function () {
			var coachModalInstance = $modal.open({
				animation: true,
				templateUrl: "page_createcourse/coachModal.html",
				controller: "CoachModalInstanceController",
				size: "lg",
				resolve: {
					externalCoaches: function() {
						return $scope.externalCoaches;
					}
				}
			});

			coachModalInstance.result.then(function (data) {
			}, function () {
			});
		};
	}])

	.controller('CoachModalInstanceController', ['$scope', '$modalInstance', 'coachService', 'externalCoaches', function($scope, $modalInstance, coachService, externalCoaches) {

		$scope.coachInfo = {
			numberPhone: ""
		};


		$scope.ok = function() {
			console.log($scope.coachInfo);
			coachService.addCoach($scope.coachInfo).then(function(data) {
				coachService.getCoaches().then(function(result) {
					externalCoaches = angular.copy(result);
					$modalInstance.close(externalCoaches);
				});
			});
		};

		$scope.cancel = function() {
			$modalInstance.dismiss('cancel');
		};
	}]);