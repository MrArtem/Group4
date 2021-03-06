var menuApp = angular.module("myApp.menuApp", ['ngAnimate']);
menuApp.directive('headerMenu', ['$document', function ($document) {
	return {
		restrict: "E",
		templateUrl: "page_common/header-menu.html",
		link: function (scope, elem, attrs) {

			$document.bind('keypress', function (e) {
				if (70 == e.keyCode && e.shiftKey) {
					debugger
					scope.openSearch();
					elem.find("#searchField").focusin();


				}
			})
		},
		controller: "HeaderMenuController",


	};
}]);

menuApp.controller("HeaderMenuController", ['$scope', '$location', "getCategories", 'userService', "notificationService", "$modal", '$translate', function ($scope, $location, getCategories, userService, notificationService, $modal, $translate) {


	$scope.createcourse = function () {
		$location.path("/createcourse");
	};

	$scope.changeLanguage = function() {
		$translate.use(($translate.use() === 'en_EN') ? 'ru_RU' : 'en_EN');
	};

	$scope.categories = [];
	$scope.username = "";

	$scope.notifications = 0;
	$scope.anim = false;
	function polling(c) {
		notificationService.notification(c).success(function (data, status) {

			console.log("getting notification " + data + ", status" + status);

			if ((typeof data) === "number") {
				// alert(data + " is Number");
				$scope.notifications = data;
				$scope.anim = !$scope.anim;
			}
			setTimeout(function () {
				polling($scope.notifications);

			}, 300);
		})
	}


	userService.loginPromise().then(function (un) {
		$scope.username = un;
		if (userService.isAdmin()) {
			polling($scope.notifications);
		}

	});
	$scope.isActive = function (viewLocation) {
		var viewLocationArray = viewLocation.split("/");
		var locationArray = $location.path().split("/");


		var active = (locationArray[1] === viewLocationArray[1]);
		return active;
	};


	$scope.logoutFunction = function () {

		userService.logout();
	}

	$scope.isAdmin = function () {
		return userService.isAdmin();
	}

	$scope.searchHistory = "";
	$scope.openSearch = function () {

		var searchModalInstance = $modal.open({
			animation: true,
			templateUrl: 'page_common/SearchModal.html',
			controller: 'SearchController',
			size: "lg",
			windowClass: "search-modal",
			resolve: {}
		});

		searchModalInstance.result.then(function (data) {

			//sending new settings
			profileService.saveSettings(data);

		}, function () {

			//cancel feedback
		});


	}
}])
	.controller("SearchController", ['$scope', '$modalInstance', "searchService", "$location", "userService", function ($scope, $modalInstance, searchService, $location, userService) {
		$scope.goto = function (path) {

			$location.path(path);
			$scope.cancel();
		}


		if (!$scope.$parent.searchHistory) {
			$scope.searchQuery = "";
		} else {
			$scope.searchQuery = $scope.$parent.searchHistory;
		}


		$scope.result = [];
		$scope.resultC = [];

		$scope.search = function () {

			$scope.$parent.searchHistory = $scope.searchQuery;

			if ($scope.searchQuery.length == 0) {
				$scope.result = [];
				$scope.resultC = [];
				$scope.message = "";
				return;
			}
			if ($scope.searchQuery.length < 3) {
				$scope.message = "More More More ..."
				$scope.result = [];
				$scope.resultC = [];
				return;
			}
			$scope.message = "Wait a sec, I got dozy backend ..."
			if (!userService.isAdmin()) {
				searchService.searchTrainigs($scope.searchQuery).success(function (data) {
					if (data.length > 0) {
						$scope.message = "";
					} else {
						$scope.message = "Oh Man, Oh Man ..."
					}

					$scope.result = data;
				});
			} else {

				searchService.searchBoth($scope.searchQuery).then(function (result) {
					console.log("Both:\n" + JSON.stringify(result));


					if (result.trainings.data.length > 0 || result.users.data.length > 0) {
						$scope.message = "";
					} else {
						$scope.message = "Oh Man, Oh Man ..."
					}

					$scope.result = result.trainings.data;
					$scope.resultC = result.users.data;


				});

			}
		}


		$scope.cancel = function () {
			$modalInstance.dismiss('cancel');
		};

		// start search from start

		$scope.search();
	}])
                          
 