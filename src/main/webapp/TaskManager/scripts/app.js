/**
 * Created by boduill on 21.01.16.
 */
var app = angular.module('TaskManager', []);

app.controller('projectController', ['$http', '$scope', function ($http, $scope) {
    $scope.user = {id: '', login: '', password: ''};
    $scope.task = {id: null, name: '', deadlineDate: '', status: '', priority: '', project: ''};
    $scope.fetchProjects = function () {
        $http.get("/" + $scope.user.login + "/projects").success(function (data) {
            $scope.projects = data;
        });
    };
    $scope.updateTask = function (task) {
        $http.post($scope.user.login + "/project/task/update", task).success(function (data) {
            $scope.fetchProjects();
        });
    };

    $scope.checkSessionStorage = function () {
        if (sessionStorage.loggedIn == 'true') {
            $scope.user = JSON.parse(sessionStorage.user);
            $scope.fetchProjects();
            $scope.$watch('loginFlag', function () {
                $scope.loginFlag.data = false;
            });
        }
    };

    $scope.checkSessionStorage();

    $scope.addTask = function (projectId, model) {
        if (model != null) {
            if (model.length > 0) {
                $scope.task.id = null;
                $scope.task.name = model;
                $scope.task.deadlineDate = null;
                $scope.task.status = null;
                $scope.task.priority = null;
                $scope.task.project = projectId;
                $http.post($scope.user.login + "/project/task/add", $scope.task).success(function () {
                    $scope.fetchProjects();
                });
            }
        }
    };

    $scope.callEditTask = function (task) {
        $scope.datapickeModel = '';
        $scope.taskNameUpdate = task.name;
        console.log(task.deadlineDate != null);
        if (task.deadlineDate != null) {
            $scope.datapickeModel = task.deadlineDate;
        }
        priority = $('#selectPriority');
        switch (task.priority) {
            case 1:
                priority.val(1);
                break;
            case 2:
                priority.val(2);
                break;
            case 3:
                priority.val(3);
                break;
        }
        statusBox = $('#status');
        switch (task.status) {
            case "In process" :
                statusBox.val("In process");
                break;
            case "Done" :
                statusBox.val("Done");
                break;
        }
    };

    $scope.updateForTask = function (task) {
        task.name = $("#taskName").val();
        task.deadlineDate = $("#datapicker").val();
        task.priority = $('#selectPriority').val();
        task.status = $('#status').val();
        $scope.updateTask(task);
        $('#updateModal').modal('hide');
        $scope.datapickeModel = '';
    };

    $scope.deleteTask = function (task) {
        $http.post($scope.user.login + "/project/task/delete", task).success(function () {
            $scope.fetchProjects();
        });
    };

    $scope.createProject = function (projectName) {
        $http.post($scope.user.id + "/project/create/" + projectName).success(function () {
            $scope.fetchProjects();
        });
        $('#createProject').modal('hide');
    };

    $scope.modalClear = function () {
        $scope.projectCreateName = '';
    };

    $scope.editProject = function (projectName) {
        $scope.projectNameUpdate = projectName;
    };

    $scope.updateProject = function (project) {
        project.name = $scope.projectNameUpdate;
        $http.post($scope.user.login + "/project/update", project).success(function () {
            $scope.fetchProjects();
            $('#updateProject').modal('hide');
        });
    };

    $scope.deleteProject = function (project) {
        $http.post($scope.user.login + "/project/delete", project).success(function () {
            $scope.fetchProjects();
        });
    };

    $scope.taskDone = function (task) {
        $scope.taskBuffer = task;
        if ($scope.taskBuffer.status == "Done") {
            $scope.taskBuffer.status = "In process";
            $scope.updateTask($scope.taskBuffer);
        } else {
            $scope.taskBuffer.status = "Done";
            $scope.updateTask($scope.taskBuffer);
        }

    };

    $scope.login = function () {
        $scope.user.id = '';
        $scope.user.login = $scope.loginIn;
        $scope.user.password = $scope.password;
        $http.post("login", $scope.user).success(function (data) {
            if (data.length == 0) {
                $scope.loginSuccess = false;
            } else {
                $scope.loginSuccess = true;
                $scope.user = data;
                sessionStorage.loggedIn = true;
                sessionStorage.user = JSON.stringify($scope.user);
                $scope.fetchProjects();
                $scope.loginFlag.data = false;
            }
        });
    };

    $scope.logOut = function () {
        sessionStorage.loggedIn = 'false';
        sessionStorage.user = '';
        $scope.loginFlag.data = true;
    };

    $scope.registrationSwitch = function () {
        $scope.registrationFlag.data = true;

    };

    $scope.switchSignIn = function () {
        $scope.registrationFlag.data = false;
        $scope.successRegistration.data = false;
    };

    $scope.registerUser = function () {
        password = $scope.regPassword;
        passwordComfirm = $scope.regPasswordConfirm;
        if (password != passwordComfirm) {
            $('#regPassword').parent().addClass('has-error');
            $('#regPasswordConfirm').parent().addClass('has-error');
        } else {
            $('#regPassword').parent().removeClass('has-error');
            $('#regPasswordConfirm').parent().removeClass('has-error');
            $scope.user.id = '';
            $scope.user.login = $scope.regLogin;
            $scope.user.password = password;
            $http.post("addUser", $scope.user).success(function (data) {
                $scope.regLogin = '';
                $scope.regPassword = '';
                $scope.regPasswordConfirm = '';
                if (!data) {
                    $scope.showLoginExist.data = true;
                } else {
                    $scope.showLoginExist.data = false;
                    $scope.registrationFlag.data = null;
                    $scope.successRegistration.data = true;
                }
            });
        }
    };

    $scope.orderProject = function (project) {
        return project.name.toLowerCase() + project.name.toLowerCase();
    }

}]);



