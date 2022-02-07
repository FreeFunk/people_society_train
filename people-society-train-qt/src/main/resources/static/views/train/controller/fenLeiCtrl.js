/**
 *  首页
 */
define(['app','commonService'], function(app){
	return app.controller('fenLeiCtrl',
        ['$scope','$rootScope','$http','$timeout','appConfig','commonService','$routeParams',
	function ($scope,$rootScope,$http,$timeout,appConfig,commonService,$routeParams) {
	    $scope.currentNav = "fenlei";

	    $scope.majorList = [];
	    $scope.courseClsList = [];
	    $scope.majorId = "";

        //查询所有的专业
        $scope.listAllMajorBySchool = function(){
            var url = "/cour/listAllMajorBySchool.jsn";
            $rootScope.request(url,{},function(data){
                if(data.success){
                    if(data.data.length > 0){
                        $scope.majorList = data.data;
                        if(data.data.length > 0){
                            $scope.majorId = data.data[0].id;
                            $scope.listAllCourseClsByMajor(data.data[0].id);
                        }
                    }
                }else{
                    $rootScope.errHttpAction(data);
                }
            })
        }

        //根据专业查询所有的课程分类
        $scope.listAllCourseClsByMajor = function(majorId){
            $scope.majorId = majorId;
            var url = "/cour/listAllCourseClsByMajor.jsn";
            var param = "ownerMajorId="+majorId;
            $rootScope.request(url,param,function(data){
                if(data.success){
                    if(data.data.length > 0){
                        $scope.courseClsList = data.data;
                        //设置默认图标
                        for(var i=0;i<$scope.courseClsList.length;i++){
                            if($scope.courseClsList[i].courseClsIcon == null){
                                $scope.courseClsList[i].gridIcon = "ys3";
                                $scope.courseClsList[i].courseClsIcon = "icon-xuexi";
                            }
                        }
                    }else{
                        $scope.courseClsList = [];
                    }
                }else{
                    $rootScope.errHttpAction(data);
                }
            })
        }


        //跳转到查询页面
        $scope.gotoSearchPage = function(){
            $rootScope.changePage("/search");
        }

        //专业和课程分类跳转到查询页面
        $scope.gotoSearchPageByMajorCls = function(clsId){
            var majorId = $scope.majorId;
            $rootScope.changePage("/search//"+majorId+"/"+clsId);
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

        //查询所有的专业
        $scope.listAllMajorBySchool();

	}]);
});