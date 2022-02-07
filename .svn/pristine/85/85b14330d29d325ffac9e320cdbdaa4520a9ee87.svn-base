/**
 *  首页
 */
define(['app','commonService'], function(app){
	return app.controller('mainCtrl',
        ['$scope','$rootScope','$http','$timeout','appConfig','commonService','$routeParams',
	function ($scope,$rootScope,$http,$timeout,appConfig,commonService,$routeParams) {
	    $scope.currentNav = "main";

		$scope.schoolInfo = {};
		//新上好课
        $scope.latelyCourseList = [];
        //进阶课程
        $scope.levelCourseList = [];
        //专业列表
        $scope.majorList = [];
        //所选的专业
        $scope.majorId = "";
        //课程分类列表
        $scope.courseClsList = [];
        //所选的课程分类
        $scope.courseClsId = "";

        //获取学校的四个专业
        $scope.selectFourMajor = function(){
            var url = "/cour/listMajorBySchoolLimit4.jsn";
            $rootScope.request(url,{},function(data){
                if(data.success){
                    $scope.majorList = data.data;
                }else{
                    $rootScope.errHttpAction(data);
                }
            })
        }

        //获取学校轮播图片
        $scope.getSchoolBanner = function(){
            var url = "/school/selectSchoolBanner.jsn";
            $rootScope.request(url,{},function(data){
                if(data.success){
                    $scope.schoolInfo.banImgList = data.data;

                    //设置轮播图
                    $timeout(function(){
                        //轮播图
                        $('[data-ydui-slider]').each(function() {
                            var mSlider = $("#m-slider");
                            mSlider.slider(window.YDUI.util.parseOptions(mSlider.data('ydui-slider')));
                        });
                    });
                }else{
                    $rootScope.errHttpAction(data);
                }
            })
        }

        //最新好课
        $scope.selectLateLyFourCourse = function(){
            var url = "/cour/selectLateLyFourCourse.jsn";
            var param = "ownerMajorId="+$scope.majorId;
            $rootScope.request(url,param,function(data){
                if(data.success){
                    $scope.latelyCourseList = data.data;
                    for(var i=0;i<$scope.latelyCourseList.length;i++){
                        if($scope.latelyCourseList[i].coursePrice == 0){
                            $scope.latelyCourseList[i].coursePrice = "免费"
                        }
                        var courseImage = $scope.latelyCourseList[i].courseImage;
                        if(courseImage && courseImage.indexOf('http')!=0){
                            courseImage = appConfig.baseImgUrl + courseImage;
                            $scope.latelyCourseList[i].courseImage = courseImage;
                        }
                    }
                }else{
                    $rootScope.errHttpAction(data);
                }
            })
        }

        //查询所有的课程分类
        $scope.listAllCourseClsByMajor = function(){
            var url = "/cour/listAllCourseClsByMajor.jsn";
            var param = "ownerMajorId="+$scope.majorId;
            $rootScope.request(url,param,function(data){
                if(data.success){
                    $scope.courseClsList = data.data;
                    if(data.data.length > 0){
                        $scope.courseClsId = data.data[0].id;
                        $scope.selectThreeCourseByCourClsAndLevel(data.data[0].id);
                    }else{
                        $scope.levelCourseList = [];
                    }
                }else{
                    $rootScope.errHttpAction(data);
                }
            })
        }

        //根据课程分类查询进阶课程
        $scope.selectThreeCourseByCourClsAndLevel = function(courseClsId){
            $scope.courseClsId = courseClsId;
            var url = "/cour/selectThreeCourseByCourClsAndLevel.jsn";
            var param = "courseClsId="+courseClsId;
            $rootScope.request(url,param,function(data){
                if(data.success){
                    $scope.levelCourseList = data.data;
                    for(var i=0;i<$scope.levelCourseList.length;i++){
                        if($scope.levelCourseList[i].coursePrice == 0){
                            $scope.levelCourseList[i].coursePrice = "免费";
                        }
                        if($scope.levelCourseList[i].totalStudentNum == null){
                            $scope.levelCourseList[i].totalStudentNum = 0;
                        }
                        //设置图片
                        var courseImage = $scope.levelCourseList[i].courseImage;
                        if(courseImage && courseImage.indexOf('http')!=0){
                            courseImage = appConfig.baseImgUrl + courseImage;
                            $scope.levelCourseList[i].courseImage = courseImage;
                        }
                    }
                }else{
                    $rootScope.errHttpAction(data);
                }
            })
        }

        $scope.freshCourseInfo = function(majorId){
            $scope.majorId = majorId;

            //最新好课
            $scope.selectLateLyFourCourse();
            //查询所有的专业
            $scope.listAllCourseClsByMajor();
        }

        //去到查询页面
        $scope.gotoSearch = function(){
            $rootScope.changePage("/search");
        }

        //跳转到课程详情
        $scope.gotoCourDetail = function(courseId){
            $rootScope.changePage("/courDetail/"+courseId);
        }

        //获取当前登录人--用于手机培训宝查询内容限制
        $scope.getCurrentUser = function(){
            var url = "/stu/getCurrentStuRole.jsn";
            $rootScope.request(url,{},function(data){
                if(data.success){

                }else{
                    $rootScope.errHttpAction(data);
                }
            })
        }

        //获取当前登录人
        $scope.getCurrentUser();
        //查询四个专业
        $scope.selectFourMajor();
        //获取学校轮播图
        $scope.getSchoolBanner();
        //查询最新课程和课程分类
        $scope.freshCourseInfo('');

	}]);
});