/**
 *  首页
 */
define(['app','commonService','ngInfiniteScroll'], function(app){
	return app.controller('examRecListCtrl',
        ['$scope','$rootScope','$http','$timeout','appConfig','commonService','$routeParams',
	function ($scope,$rootScope,$http,$timeout,appConfig,commonService,$routeParams) {
        $scope.courseId = $routeParams.courseId;
        $scope.pageInfoObj = {};

        //查询考试列表
        $scope.selectExamRecList = function(){
            var url = "/test/selectExamRecList.jsn";
            var param = "queryObj.ownerCourseId="+$scope.courseId;
            commonService.generatePageObj($scope.pageInfoObj,url,8);
            $scope.pageInfoObj.param = param;
            $scope.nextExamRecPage();
        }

        //下一页
        $scope.nextExamRecPage = function(){
            commonService.getNextPageObjectByCondition($scope.pageInfoObj);
        }

        //去到试卷详情界面
        $scope.gotoExamResultPage = function(paperId){
            $rootScope.changePage("/examResult/"+paperId);
        }

        //查询考试列表
        $scope.selectExamRecList();

	}]);
});