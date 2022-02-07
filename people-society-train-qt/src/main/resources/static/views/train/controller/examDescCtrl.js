/**
 *  首页
 */
define(['app','commonService'], function(app){
	return app.controller('examDescCtrl',
        ['$scope','$rootScope','$http','$timeout','appConfig','commonService','$routeParams',
	function ($scope,$rootScope,$http,$timeout,appConfig,commonService,$routeParams) {
		$scope.courseId = $routeParams.courseId;
		$scope.testPaper = {};
		$scope.createPaperState = "0";

		//查询试卷信息
		$scope.selectTestPaper = function(courseId){
			var url = "/test/selectTestPaper.jsn";
			var param = "courseId="+courseId;
			$rootScope.request(url,param,function(data){
				if(data.success){
                    $scope.testPaper = data.data;
				}else{
					$rootScope.errHttpAction(data);
				}
			})
		}

		//查询学员试卷信息
		$scope.createStudentPaper  = function(courseId){
			var url = "/test/selectStudentTestPaper.jsn";
			var param = "courseId="+courseId;
			$rootScope.request(url,param,function(data){
				if(data.success){
					if(data.data != null){
						var paperId = data.data.id;
                        $rootScope.changePage("/exam/"+paperId);
					}else{
                        $rootScope.alertError("未查询到信息！");
					}
				}else{
					$rootScope.errHttpAction(data);
				}
			})
		}

		//去到试卷界面
		$scope.gotoExamPage = function(){
			var courseId = $scope.courseId;
			if($scope.createPaperState == '0'){
                $scope.createPaperState = '1'
                $scope.createStudentPaper(courseId);
			}else{
                $scope.createPaperState == '1';
			}
		}

		//查询试卷信息
        $scope.selectTestPaper($scope.courseId);

	}]);
});