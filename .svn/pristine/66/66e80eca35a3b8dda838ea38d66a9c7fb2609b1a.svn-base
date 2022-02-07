/**
 *  首页
 */
define(['app','commonService','ngInfiniteScroll'], function(app){
	return app.controller('searchCtrl',
        ['$scope','$rootScope','$http','$timeout','appConfig','commonService','$routeParams',
	function ($scope,$rootScope,$http,$timeout,appConfig,commonService,$routeParams) {
        $scope.courseName = "";
        $scope.majorId = $routeParams.majorId;
        $scope.courseClsId = $routeParams.courseClsId;
        $scope.pageInfoObj = {};
        $scope.history = [];

        var searchHistory = commonService.getValueFromLocalStorage("searchHistory");
        if(searchHistory != null){
            $scope.history = searchHistory.split(",");
        }

        //清空历史
        $scope.clearSearchHistory = function(){
            $scope.history = [];
            commonService.setValueToLocalStorage("searchHistory","");
        }

        //添加查询历史
        $scope.insertSearchHistory = function(courseName){
            if(courseName.trim() != ''){
                var searchHistory = commonService.getValueFromLocalStorage("searchHistory");
                if(searchHistory == null || searchHistory == ''){
                    searchHistory = courseName;
                }else{
                    if(searchHistory.indexOf(courseName) == -1){
                        searchHistory += "," + courseName;
                    }
                }
                commonService.setValueToLocalStorage("searchHistory",searchHistory);
                $scope.history = searchHistory.split(",");
            }
        }

        //初始化数据方法
        $scope.searchCourseListPage = function(){
            if($scope.courseName != ''){
                $scope.insertSearchHistory($scope.courseName);
            }

            var url = "/cour/schoolCourseListPage.jsn";
            var param = "queryObj.courseName="+$scope.courseName
            commonService.generatePageObj($scope.pageInfoObj,url,5);
            $scope.pageInfoObj.param = param;
            $scope.nextCoursePage();
        }

        //动态参数查询数据
        $scope.courseListPage = function(param){
            var url = "/cour/schoolCourseListPage.jsn";
            commonService.generatePageObj($scope.pageInfoObj,url,5);
            $scope.pageInfoObj.param = param;
            $scope.nextCoursePage();
        }

        //下一页
        $scope.nextCoursePage = function(){
            var promis = commonService.getNextPageObjectByCondition($scope.pageInfoObj);
            if(promis){
                promis.then(function(list){
                    for(i=0;i<list.length;i++){
                        //设置图片
                        var courseImage = list[i].courseImage;
                        if(courseImage && courseImage.indexOf('http')!=0){
                            courseImage = appConfig.baseImgUrl + courseImage;
                            list[i].courseImage = courseImage;
                        }
                    }
                });
            }
        }

        //初始化查询
        if(typeof($scope.majorId) != 'undefined' && $scope.majorId != ''){
            var param = "queryObj.ownerMajorId="+$scope.majorId;
            param += "&queryObj.courseClsId="+$scope.courseClsId;
            $scope.courseListPage(param);
        }

        //搜索历史查询
        $scope.historySearch = function(courseName){
            var param = "queryObj.courseName="+courseName;
            $scope.courseListPage(param);
        }

        //跳转到课程详情
        $scope.gotoCourDetail = function(courseId){
            $rootScope.changePage("/courDetail/"+courseId);
        }

	}]);
});