/**
 *  首页
 */
define(['app','commonService'], function(app){
	return app.controller('editPwdCtrl',
        ['$scope','$rootScope','$http','$timeout','appConfig','commonService','$routeParams',
	function ($scope,$rootScope,$http,$timeout,appConfig,commonService,$routeParams) {

        //学员修改密码
        $scope.studentUpPwd = function(){
            var oldPwd = $("#oldPwd").val();
            if(oldPwd == ''){
                $rootScope.alertError("请输入原始密码！");
                return false;
            }
            var password = $("#password").val();
            if(password == ''){
                $rootScope.alertError("请输入新密码！");
                return false;
            }
            if(oldPwd == password){
                $rootScope.alertError("新密码不能和原始密码相同！");
                return false;
            }
            var conPassword = $("#conPassword").val();
            if(conPassword == ''){
                $rootScope.alertError("请输入确认密码！");
                return false;
            }
            if(password != conPassword){
                $rootScope.alertError("两次输入的密码不同！");
                return false;
            }
            var url = "/stu/studentUpPwd.jsn";
            var param = $("#studentUpPwdForm").serialize();
            $rootScope.request(url,param,function(data){
                if(data.success){
                    $rootScope.alert('修改成功！',function(e){
                        if (e) {
                            $rootScope.changePage("/userInfo");
                        }
                    });
                }else{
                    $rootScope.errHttpAction(data);
                }
            })
        }

	}]);
});