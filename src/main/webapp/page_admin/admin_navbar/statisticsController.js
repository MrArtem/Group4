/**
 * Created by Yoga 3 Pro on 30.07.2015.
 */
angular.module('myApp.admin')
    .controller('StatisticsController', ['$scope', '$http', '$filter', 'adminService', function($scope, $http, $filter, adminService) {
		$scope.temp = {};
		$scope.temp.tempDates = [];

      /*  $scope.users = ['User1', 'User2' ];*/
        $scope.trainings = ['Training1', 'Training2'];
        $scope.types = ['Select Training', 'Select Listener'];
		$scope.statisticType = ['1', '2', '3'];
        $scope.currentType = true;
        $scope.currentType2 = true;
        $scope.fullstat = true;
        $scope.partstat = true;

		$http.get('http://localhost:8080/user_controller/select_all_users_login').success(function(usersData) {
			$scope.users = usersData;
			console.log(usersData);
		});

		/*$http.get('http://localhost:8080/').success(function(trainingData) {
			$scope.trainings = trainingData;
			console.log(trainingData);
		});*/

		$scope.save = function (userselect){
			userselect.dateFrom = $filter('date')($scope.temp.tempDates[0], 'yyyy-MM-dd');
			userselect.dateTo = $filter('date')($scope.temp.tempDates[1], 'yyyy-MM-dd');
			if (userselect.dateFrom>userselect.dateTo){
				alert ('You are wrong! Check the second date!');
			}
			/*if (userselect.type==='Full statistic'){
				userselect.type='1';
			}
			if (userselect.type==='Dates of pass'){
				userselect.type='2';
			}
			if (userselect.type==='Count of pass'){
				userselect.type='3';
			}*/
			alert(userselect.type);
			if (userselect.type == null){
				alert ('Please select type of statistic!');
			}
			if (userselect.trainingName == null){
				userselect.trainingName = "";
			}
			if (userselect.userLogin == null){
				userselect.userLogin = "";
			}
			console.log(userselect);
			debugger
			adminService.sendStatistics(userselect);
			};

    }]);