/**
 *  首页
 */
define(['app','commonService'], function(app){
	return app.controller('examResultCtrl',
        ['$scope','$rootScope','$http','$timeout','appConfig','commonService','$routeParams',
	function ($scope,$rootScope,$http,$timeout,appConfig,commonService,$routeParams) {
		$scope.stuPaperId = $routeParams.paperId;
		$scope.getScore = 0;
        $scope.rightQuesCount = 0;
		$scope.stuTestPaper = {};
        $scope.currentNav = "";

		//查询学员试卷信息
		$scope.selectStudentPaper  = function(stuPaperId){
			var url = "/test/selectStudentTestPaperById.jsn";
			var param = "stuPaperId="+stuPaperId;
			$rootScope.request(url,param,function(data){
				if(data.success){
					if(data.data != null){
                        $scope.stuTestPaper = data.data.stuPaper;
                        $scope.getScore = data.data.stuPaper.getScore;
                        $scope.rightQuesCount = data.data.rightQuesCount;
					}else{
                        $rootScope.alertError("未查询到信息！");
					}
				}else{
					$rootScope.errHttpAction(data);
				}
			})
		}

		//去到试卷详情页面
		$scope.gotoExamDetail = function(){
			$rootScope.changePage("/examDetail/"+$scope.stuPaperId+"/"+$scope.getScore+"/"+$scope.rightQuesCount);
		}

		//去到证书信息页面
		$scope.gotoCertInfoPage = function(courseId){
			var url = "/stu/selectStuCertInfoByCourse.jsn";
			var param = "courseId="+courseId;
			$rootScope.request(url,param,function(data){
				if(data.success){
					var certId = data.data.id;
					$rootScope.changePage("/certInfo/"+certId);
				}else{
					$rootScope.errHttpAction(data);
				}
			})
		}


		//查询学员试卷信息
        $scope.selectStudentPaper($scope.stuPaperId);

	}]);
});