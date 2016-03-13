'use strict';

describe('TaskManager', function () {
    beforeEach(module('TaskManager'));

    var projectName = 'test project';
    var controller, scope, $httpBackend;
    var projectsDB = [{
        id: 333,
        name: "Create project",
        user: 111,
        tasks: [{
            id: 333,
            name: "Create Git repository",
            createDate: "2012-12-21",
            deadlineDate: "2012-12-21",
            status: "In process",
            priority: 3,
            project: 333
        }, {
            id: 444,
            name: "Create entities",
            createDate: "2012-12-21",
            deadlineDate: "2012-12-21",
            status: "Done",
            priority: 3,
            project: 333
        }]
    }];

    beforeEach(inject(function ($controller, $rootScope, _$httpBackend_) {
        $httpBackend = _$httpBackend_;
        $httpBackend.whenGET('/ivan/projects').respond(projectsDB);
        $httpBackend.whenPOST("ivan/project/task/add").respond(function (method, url, data, headers, keys) {
            var taskToAdd = {id: '', name: '', createDate: '', deadlineDate: '', status: '', priority: '', project: ''};
            taskToAdd = JSON.parse(data);
            projectsDB[0].tasks.push(taskToAdd);
            return [201, ''];
        });
        $httpBackend.whenPOST("ivan/project/task/update").respond(function (method, url, data, headers, keys) {
            projectsDB[0].tasks[1] = data;
            return [201, ''];
        });
        $httpBackend.whenPOST("ivan/project/task/delete").respond(function (method, url, data, headers, keys) {
            projectsDB[0].tasks.forEach(function(item, i, data) {
                if (data.id == item.id) {
                    projectsDB[0].tasks.splice(i, 1);
                }
            });
            return [201, ''];
        });
        $httpBackend.whenPOST("111/project/create/" + projectName).respond(function (method, url, data, headers, keys) {
            var projectToPush = {id: 888, name: projectName, user: '111', tasks: []};
            projectsDB.push(projectToPush);
            return [201, ''];
        });
        $httpBackend.whenPOST("ivan/project/update").respond(function (method, url, data, headers, keys) {
            var dataToInsert = {id: '', name: '', user: '', tasks: []};
            dataToInsert = JSON.parse(data);
            projectsDB.forEach(function(item, i, array) {
                if (dataToInsert.id == item.id) {
                    projectsDB[i] = dataToInsert;
                }
            });
            return [201, ''];
        });
        $httpBackend.whenPOST("ivan/project/delete").respond(function (method, url, data, headers, keys) {
            var dataToDelete = {id: '', name: '', user: '', tasks: []};
            dataToDelete = JSON.parse(data);
            projectsDB.forEach(function(item, i, array) {
                if (dataToDelete.id == item.id) {
                    projectsDB.splice(i, 1);
                }
            });
            return [201, ''];
        });
        scope = $rootScope.$new();
        controller = $controller('projectController', {
            $scope: scope

        });
    }));

    afterEach(function () {
        $httpBackend.verifyNoOutstandingExpectation();
        $httpBackend.verifyNoOutstandingRequest();
    });

    describe('projectController', function () {
        it('checking projects fetching', function () {
            expect(controller).toBeDefined();

        });

        it('fetchProjects test', function () {
            scope.user = {id: '111', login: 'ivan', password: 'secret'};
            scope.fetchProjects();
            $httpBackend.flush();
            expect(scope.projects).toEqual(projectsDB);
        });

        it('addTask test', function () {
            scope.user = {id: '111', login: 'ivan', password: 'secret'};
            scope.task = {id: null, name: '', deadlineDate: '', status: '', priority: '', project: ''};
            scope.fetchProjects();
            scope.addTask(333, 'test task');
            $httpBackend.flush();
            expect(scope.projects).toEqual(projectsDB);
        });

        it('update task test', function () {
            scope.user = {id: '111', login: 'ivan', password: 'secret'};
            scope.task = {
                id: 444,
                name: "Create entities",
                createDate: "2012-12-21",
                deadlineDate: "2012-12-21",
                status: "Done",
                priority: 3,
                project: 333
            };
            scope.fetchProjects();
            scope.updateTask(scope.task);
            $httpBackend.flush();
            expect(scope.projects).toEqual(projectsDB);
        });

        it('delete task test', function() {
            scope.user = {id: '111', login: 'ivan', password: 'secret'};
            scope.task = {
                id: 444,
                name: "Create entities",
                createDate: "2012-12-21",
                deadlineDate: "2012-12-21",
                status: "Done",
                priority: 3,
                project: 333
            };
            scope.fetchProjects();
            scope.deleteTask(scope.task);
            $httpBackend.flush();
            expect(scope.projects).toEqual(projectsDB);
        });

        it('createProject test', function() {
            scope.user = {id: '111', login: 'ivan', password: 'secret'};
            scope.fetchProjects();
            scope.createProject(projectName);
            $httpBackend.flush();
            expect(scope.projects).toEqual(projectsDB);
        });

        it('modalClear test', function() {
            scope.user = {id: '111', login: 'ivan', password: 'secret'};
            scope.createEdit = {data: false};
            scope.modalClear();
            expect(scope.createEdit.data).toEqual(true);
        });

        it('editProject test', function() {
            scope.createEdit = {data: true};
            scope.editProject(projectName);
            expect(scope.createEdit.data).toEqual(false);
        });

        it('updateProject test', function() {
            scope.user = {id: '111', login: 'ivan', password: 'secret'};
            scope.projectNameUpdate = 'update project';
            var projectToUpdate = {id: 888, name: 'test project', user: '111', tasks: []};
            scope.fetchProjects();
            scope.updateProject(projectToUpdate);
            $httpBackend.flush();
            expect(scope.projects).toEqual(projectsDB);
        });

        it('deleteProject test', function() {
            scope.user = {id: '111', login: 'ivan', password: 'secret'}
            var projectToDelete = {id: 888, name: 'update project', user: '111', tasks: []};
            scope.fetchProjects();
            scope.deleteProject(projectToDelete);
            $httpBackend.flush();
            expect(scope.projects).toEqual(projectsDB);
        });
    });

});