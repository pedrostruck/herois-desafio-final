moduleApp.controller("batalhaCtrl", batalhaCtrl);

batalhaCtrl.$Inject = ["$scope", "$location", "batalhaService","jogadorService"];


function batalhaCtrl($scope, batalhaService,$location,jogadorService) {
	const vm = this;

	vm.telaVsJogador = true;

	vm.jogador = jogadorService.jogador;
	
	vm.jogador2 = {
		nickname: null,
		heroi: {}
	};

	vm.vencedor = {
		nickname: null,
		heroi: {}
	};


	vm.batalharVsJogador = function (jogador,jogador2) {
		batalhaService.batalharVsJogador(jogador,jogador2).success(function (data) {
			vm.vencedor = data;
		}).error(function (data, status) {
			vm.message = "Aconteceu um problema: " + data;
		});
	};

	vm.batalharVsComputador = function () {
		batalhaService.batalharVsComputador().success(function (data) {
			vm.vencedor = data;
		}).error(function (data, status) {
			vm.message = "Aconteceu um problema: " + data;
		});
	};

	vm.irTelaVsComputador = function () {
		vm.telaVsComputador = true;
		vm.telaVsJogador = false;
		vm.batalhaVsComputador();
	}

	vm.irTelaVsJogador = function () {
		vm.telaVsJogador = true;
		vm.telaVsComputador = false;
	}

	

}