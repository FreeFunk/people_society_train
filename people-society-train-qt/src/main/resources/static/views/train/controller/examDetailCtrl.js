/**
 *  首页
 */
define(['app','commonService','ngInfiniteScroll'], function(app){
	return app.controller('examDetailCtrl',
        ['$scope','$rootScope','$http','$timeout','appConfig','commonService','$routeParams',
	function ($scope,$rootScope,$http,$timeout,appConfig,commonService,$routeParams) {
		$scope.stuPaperId = $routeParams.paperId;
		$scope.getScore = $routeParams.getScore;
		$scope.rightQuesCount = $routeParams.rightQuesCount;
        $scope.pageInfoObj = {};

        //查询学员试卷
        $scope.searchStuQuestionListPage = function(){
            var url = "/test/selectStudentTestPaperQuestion.jsn";
            var param = "queryObj.ownerTestPaperId="+$scope.stuPaperId;
            commonService.generatePageObj($scope.pageInfoObj,url,5);
            $scope.pageInfoObj.param = param;
            $scope.nextQuestionPage();
        }

        //下一页
        $scope.nextQuestionPage = function(){
            var promis = commonService.getNextPageObjectByCondition($scope.pageInfoObj);
            if(promis){
                promis.then(function(list){
                    //设置点击量和图片
                    for(i=0;i<list.length;i++){
                        var question = list[i];
                        var testPaperQuestionAnalysis = question.testPaperQuestionAnalysis;
                        if(!testPaperQuestionAnalysis || testPaperQuestionAnalysis==''){
                            question.testPaperQuestionAnalysis = "暂无解析";
                        }
                    }
                });
            }
        }

        //跳到课程列表
        $scope.gotoCourseListPage = function(){
            $rootScope.changePage("/courseList");
        }

        //查询学员答题情况
        $scope.searchStuQuestionListPage();

	}]);
});