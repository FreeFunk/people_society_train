/**
 *  首页
 */
define(['app','commonService'], function(app){
	return app.controller('loginCtrl',
        ['$scope','$rootScope','$http','$timeout','appConfig','commonService','$routeParams',
	function ($scope,$rootScope,$http,$timeout,appConfig,commonService,$routeParams) {
        $scope.studentPhoneNum = "";
        $scope.password = "";

	    //用户登录
        $scope.studentLogin = function(){
            if($scope.studentPhoneNum=='' && !commonService.checkPhoneNum($scope.studentPhoneNum)){
                $rootScope.alertError('请输入正确格式的手机号！');
                return false;
            }
            if($scope.password == ''){
                $rootScope.alertError('密码不能为空！');
                return false;
            }
            var url = "/stu/studentLogin.jsn";
            if(isWx()){
                url = "/stu/studentLoginWx.jsn";
            }
            var param = $("#studentLoginForm").serialize();
            $rootScope.request(url,param,function(data){
                if(data.success){
                    var student = data.data;
                    if(student != null){
                        var isUpPwd = student.isUpPwd;
                        if(isUpPwd == '1'){
                            $rootScope.changePage("/courseList");
                        }else{
                            $rootScope.alertConfirm('登录成功，请修改默认密码',function(e){
                                if (e) {
                                    $rootScope.changePage("/editPwd");
                                }else{
                                    $rootScope.changePage("/courseList");
                                }
                            });
                        }
                    }else{
                        $rootScope.alertError("学员信息不存在！");
                    }
                }else{
                    $rootScope.errHttpAction(data);
                }
            });
        }

        //学员修改密码
        $scope.studentUpPwd = function(){
            var password = $("#password").val();
            if(password == ''){
                $rootScope.alertError("请输入新密码！");
                return false;
            }
            var conPassword = $("#conPassword").val();
            if(conPassword == ''){
                $rootScope.alertError("请输入确认密码！");
                return false;
            }
            if(password != conPassword){
                $rootScope.alertError("确认密码与新密码不一样！");
                return false;
            }
            var url = "/stu/studentUpPwd.jsn";
            var param = $("#studentUpPwdForm").serialize();
            $rootScope.request(url,param,function(data){
                if(data.success){
                    $rootScope.alert('修改成功！',function(e){
                        if (e) {
                            $rootScope.changePage("/main");
                        }
                    });
                }else{
                    $rootScope.errHttpAction(data);
                }
            })
        }

        //跳转到注册页面
        $rootScope.gotoRegisterPage = function(){
            if(getOs()=='pc'){
                $rootScope.changePage("/registerWeb");
            }else{
                $rootScope.changePage("/register");
            }
        }

	}]);
});