/**
 *  首页
 */
define(['app','commonService'], function(app){
	return app.controller('examListCtrl',
        ['$scope','$rootScope','$http','$timeout','appConfig','commonService','$routeParams',
	function ($scope,$rootScope,$http,$timeout,appConfig,commonService,$routeParams) {
		$scope.pageInfoObj = {};
		$scope.paperState = "ok";

		//查询课程列表
        $scope.searchStuCourseListPage = function(){
            var url = "/stuCourse/stuCourseListPageForExam.jsn";
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

        //去到试卷简介
        $scope.gotoExamDescPage = function(courseId){
            var url = "/test/checkTestPaperByCourse.jsn";
            var param = "courseId="+courseId;
            $rootScope.request(url,param,function(data){
                if(data.success){
                    if(data.data == null){
                        $rootScope.alertSuccess("该门课程还未安排考试！");
                    }else{
                        $rootScope.changePage("/examDesc/"+courseId);
                    }
                }else{
                    $rootScope.errHttpAction(data);
                }
            })
        }

        //去到考试记录列表
        $scope.gotoExamRecListPage = function(courseId){
            $rootScope.changePage("/examRecList/"+courseId);
        }

        //查询学员关联的课程
        $scope.searchStuCourseListPage();

	}]);
});