/**
 * Created by boduill on 21.01.16.
 */
var app = angular.module('TaskManager', []);

app.controller('projectController', ['$http', '$scope', function ($http, $scope) {
    $scope.user = {id: '', login: '', password: ''};
    $scope.task = {id: null, name: '', deadlineDate: '', status: '',priority: '', project: ''};
    $scope.fetchProjects = function() {
        $http.get("/" + $scope.user.login + "/projects").success(function(data) {
            $scope.projects = data;
            console.log(data);
        });
    };
    $scope.updateTask = function(task) {
        $http.post($scope.user.name + "/project/task/update", task).success(function(data) {
            $scope.fetchProjects();
        });
    };

    $scope.checkSessionStorage = function() {
        console.log(sessionStorage.loggedIn);
          if (sessionStorage.loggedIn == 'true') {
              console.log("in");
              $scope.user = JSON.parse(sessionStorage.user);
              $scope.fetchProjects();
              $scope.$watch('loginFlag', function () {
                  $scope.loginFlag  = false;
              });

          }
    };

    $scope.checkSessionStorage();

    $scope.addTask = function(projectId, model) {
        if (model != null) {
            if (model.length > 0) {
                $scope.task.id = null;
                $scope.task.name = model;
                $scope.task.deadlineDate = null;
                $scope.task.status = null;
                $scope.task.priority = null;
                $scope.task.project = projectId;
                $http.post($scope.user.name + "/project/task/add", $scope.task).success(function(data) {
                    $scope.fetchProjects();
                });
            }
        }
    };
    $scope.callEditTask = function(task) {
        $('#taskName').val(task.name);
        if (task.deadlineDate != null) {
            $('#datapicker').val(task.deadlineDate);
        }
        priority = $('#selectPriority');
        switch (task.priority) {
            case 1: priority.val(1); break;
            case 2: priority.val(2); break;
            case 3: priority.val(3); break;
        }
        statusBox = $('#status');
        switch (task.status) {
            case "In process" : statusBox.val("In process"); break;
            case "Done" : statusBox.val("Done"); break;
        }
    };

    $scope.updateForTask = function(task) {
        task.name = $("#taskName").val();
        task.deadlineDate = $("#datapicker").val();
        task.priority = $('#selectPriority').val();
        task.status = $('#status').val();
        $scope.updateTask(task);
    };

    $scope.deleteTask = function(task) {
        $http.post($scope.user.name + "/project/task/delete", task).success(function(data) {
            $scope.fetchProjects();
        });
    };

    $scope.createProject = function(projectName) {
        $http.post($scope.user.id + "/project/create/" + projectName).success(function(data) {
            $scope.fetchProjects();
        });
    };

    $scope.modalClear = function() {
        $scope.createEdit.data = true;
        $('#projectName').val('');
    };

    $scope.editProject = function(projectName) {
        $scope.createEdit.data = false;
        $('#projectName').val(projectName);
    };

    $scope.updateProject = function(project) {
        project.name = $('#projectName').val();
        $http.post($scope.user.name + "/project/update", project).success(function(data) {
            $scope.fetchProjects();
        });
    };

    $scope.deleteProject = function(project) {
        $http.post($scope.user.name + "/project/delete", project).success(function(data) {
            $scope.fetchProjects();
        });
    };

    $scope.rowSelected = function(task) {

    };

    $scope.taskDone = function(task) {
        $scope.taskBuffer = task;
        if (task.status == "Done") {
            $scope.taskBuffer.status = "In process";
            $scope.updateTask($scope.taskBuffer);
        } else {
            $scope.taskBuffer.status = "Done";
            $scope.updateTask($scope.taskBuffer);
        }

    };

    $scope.login = function() {
        login = $('#login').val().trim();
        password = $('#password').val();
        console.log(login.length);
        console.log(password.length);
        if (login.length == 0 && password.length == 0) {
            $('#login').parent().addClass('has-error');
            $('#password').parent().addClass('has-error');
        }
        else if (login.length == 0) {
            $('#password').parent().removeClass('has-error');
            $('#login').parent().addClass('has-error');
        } else if (password.length == 0) {
            $('#login').parent().removeClass('has-error');
            $('#password').parent().addClass('has-error');
        } else {
            $('#login').parent().removeClass('has-error');
            $('#password').parent().removeClass('has-error');
            $scope.user = {id: '', login: '', password: ''};
            $scope.user.login = $('#login').val();
            $scope.user.password = $('#password').val();
            $http.post("login", $scope.user).success(function(data) {
                if (data.length == 0) {
                    $scope.loginSuccess = false;
                } else {
                    console.log("data not null");
                    $scope.loginSuccess = true;
                    $scope.user = data;
                    sessionStorage.loggedIn = true;
                    sessionStorage.user = JSON.stringify($scope.user);
                    console.log($scope.user);
                    $scope.fetchProjects();
                    $scope.$watch('loginFlag', function () {
                        $scope.loginFlag  = false;
                    });


                }
            });
        }
    }

}]);



