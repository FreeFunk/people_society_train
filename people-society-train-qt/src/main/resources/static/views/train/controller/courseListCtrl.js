/**
 *  首页
 */
define(['app','commonService','ngInfiniteScroll'], function(app){
	return app.controller('courseListCtrl',
        ['$scope','$rootScope','$http','$timeout','appConfig','commonService','$routeParams',
	function ($scope,$rootScope,$http,$timeout,appConfig,commonService,$routeParams) {
	    $scope.currentNav = "courseList";
        $scope.pageInfoObj = {};

        //初始化数据方法
        $scope.searchStuCourseListPage = function(){
            var url = "/stuCourse/stuCourseListPage.jsn";
            commonService.generatePageObj($scope.pageInfoObj,url,5);
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



        //跳转到学习界面
        $scope.gotoCourDetail = function(stuCourse){
            var courseId = stuCourse.id;
            var payState = stuCourse.payState;
            if(getOs()=='pc'){
                if(payState!=null && payState=='1'){
                    $rootScope.changePage("/sdudyweb/"+courseId);
                }else {
                    $rootScope.alertError('请先去微信端购买课程!');
                }
            }else{
                if(payState!=null && payState=='1'){
                    $rootScope.changePage("/sdudy/"+courseId);
                }else {
                    $rootScope.changePage("/orderPay/"+courseId);
                }
            }
        }

        //跳转到查询界面
        $scope.gotoSearchPage = function(){
            $rootScope.changePage("/search");
        }

        //查询学员关联的课程
        $scope.searchStuCourseListPage();

	}]);
});