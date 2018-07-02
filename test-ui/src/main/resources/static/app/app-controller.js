'use strict';

angular.module('test').controller('AppController', ['$scope', '$state','$http',
	function ($scope, $state,$http) {
	
	$scope.usuario={};
	$scope.usuarios=[];
	
	function carregarClientes(){
	$http({method:"GET",url:"http://localhost:5000/api/user"})
	.then(function(response){
		$scope.usuarios=response.data;
	},function(response){
		console.log(response.data);
		console.log(response.status);
	});
	};
	
	$scope.salvarCliente=function(){
		if($scope.frmUsuario.$valid){
			$http({method:"POST",url:"http://localhost:5000/api/user",data:$scope.usuario})
			.then(function(response){
				carregarClientes()
				$scope.cancelarAlteracaoCliente;
				$scope.frmUsuario.$setPristine(true);
			},function(response){
				console.log(response.data);
				console.log(response.status);
			});
		}else{
			window.alert("Dados invalidos");
		}
	};
	
	$scope.excluirUsuario=function(usuario){
		$http({method:"DELETE",url:"http://localhost:5000/api/user/" + usuario.id})
		.then(function(response){
			$scope.usuarios.splice($scope.usuarios.indexOf(usuario),1);
		},function(response){
			console.log(response.data);
			console.log(response.status);
		});
	};
	
	$scope.alterarUsuario=function(usuario){
		$scope.usuario = angular.copy(usuario);
	};
	
	$scope.cancelarAlteracaoUsuario=function(){
		$scope.usuario={};
	}
	
	carregarClientes();
}]);