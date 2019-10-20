moduleApp.controller("JogadorController", JogadorController);

JogadorController.$Inject = ["$scope", "$location", "jogadorService"];

function JogadorController($scope, $location, jogadorService) {
    const self = this;

    self.jogador = {
        nickname: null,
        senha: null,
        personagem: {}
    };

    self.login = function (dadosLogin) {
        dadosLogin.senha = btoa(dadosLogin.senha);
        jogadorService.login(dadosLogin)
            .then(function (response) {
                jogadorService.jogador = response.data;
                alert("Logado com Sucesso");
                $location.path("/batalha");
            }, function(error){
                alert(error.data);
            });
    };

    self.cadastrar = function(jogador) {
        jogador.senha = btoa(jogador.senha);
        jogadorService.inserirJogador(jogador)
        .then(function(response) {
            alert("Cadastrado com Sucesso");
            $location.path("/login");
        }, function(error) {
           alert(error.data.mensagem);
        });
    };

    self.irTela = function (path) {
        $location.path(path);
    };
};