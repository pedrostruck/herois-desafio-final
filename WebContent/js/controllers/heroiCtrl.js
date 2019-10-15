angular.module("heroi").controller("heroiCtrl", function ($scope, heroisService) {
	var vm = this;
	vm.app = "Heróis";
	vm.service = heroisService;
	vm.herois = [];

	vm.init = function(){
		vm.login = true;
		vm.carregarHerois();
	}

	vm.carregarHerois = function () {
		vm.service.getHerois().success(function (data) {
			vm.herois = data;
		}).error(function (data, status) {
			vm.message = "Aconteceu um problema: " + data;
		});
	};

	vm.irCadastrar = function () {
		vm.cadastrar = true;
		vm.login = false;
	}

	vm.irLogin = function () {
		vm.login = true;
		vm.cadastrar = false;
	}
});