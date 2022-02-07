/**
 *  首页
 */
define(['app','commonService'], function(app){
	return app.controller('userInfoCtrl',
        ['$scope','$rootScope','$http','$timeout','appConfig','commonService','$routeParams',
	function ($scope,$rootScope,$http,$timeout,appConfig,commonService,$routeParams) {
	    $scope.currentNav = "userInfo";
        $scope.curStudent = {};

        /*获取学员信息*/
	    $scope.getUserInfo = function(){
	        var url = "/stu/getUserInfoAndExtendInfo.jsn";
	        $rootScope.request(url,{},function(data){
                if(data.success){
                    $scope.curStudent = data.data.student;
                    $scope.curStudent.certCount = data.data.certCount;
                    $scope.curStudent.courseCount = data.data.courseCount;

                    //设置图片
                    var headPhoto = $scope.curStudent.headPhoto;
                    if(headPhoto && headPhoto.length>0&&headPhoto.indexOf("http")!=0){
                        headPhoto =  appConfig.baseImgUrl + headPhoto;
                        $scope.curStudent.headPhoto = headPhoto;
                    }else{
                        headPhoto = "images/user-img.jpg";
                        $scope.curStudent.headPhoto = headPhoto;
                    }
                    //昵称
                    /*var nickName = $scope.curStudent.nickName;
                    if(nickName == null){
                        nickName = "请设置昵称";
                        $scope.curStudent.nickName = nickName;
                    }*/
                }else{
                    $rootScope.errHttpAction(data);
                }
            })
        }



        //跳转到订单界面
        $scope.gotoOrder = function(){
            $rootScope.changePage("/order");
        }

        //退出--跳转到登录界面
        $scope.gotoLogin = function(){
            var url = "/stu/studentLogout.jsn";
            $rootScope.request(url,{},function(data){
                if(data.success){
                    //跳转到登录页面
                    if(getOs()=='pc') {
                        $rootScope.changePage("/loginWeb");
                    }else {
                        $rootScope.changePage("/login");
                    }
                   /* $rootScope.changePage("/login");*/
                }else{
                    $rootScope.errHttpAction(data);
                }
            })
        }

        //跳转到更新学员信息界面
        $scope.gotoEditPage = function(){
            $rootScope.changePage("/editUser");
        }

        //跳转到修改密码界面
        $scope.gotoEditPwdPage = function(){
            $rootScope.changePage("/editPwd");
        }

        //跳转到考试列表
        $scope.gotoExamListPage = function(){
            $rootScope.changePage("/examList");
        }

        //跳转到证书信息页面
        $scope.gotoCertListPage = function(){
            $rootScope.changePage("/certRecList");
        }

        //获取学员信息
        $scope.getUserInfo();

	}]);
});