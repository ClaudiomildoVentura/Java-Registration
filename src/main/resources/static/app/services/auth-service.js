angular.module('AddressBook')
	// Creating the Angular Service for storing logged user details
	.service('AuthService', function () {
		return {
			user: null
		}
	});