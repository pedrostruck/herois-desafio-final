angular.module("heroiApp").controller("HomeController", function ($scope, heroisService) {
	var self = this;
	self.appName = "Heróis App";
	self.service = HeroiService;
	self.herois = [];

	self.init = function() {
		self.login = true;
		self.carregarHerois();
	}

	self.carregarHerois = function () {
		self.service.getHerois().success(function (data) {
			self.herois = data;
		}).error(function (data, status) {
			self.message = "Aconteceu um problema: " + data;
		});
	};

	self.irCadastrar = function () {
		self.cadastrar = true;
		self.login = false;
	}

	self.irLogin = function () {
		self.login = true;
		self.cadastrar = false;
	}
});