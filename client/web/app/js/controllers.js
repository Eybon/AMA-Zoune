/**
 * Maxime BERGEON
 * Antoine Forgerou
 * 18/11/2015
 */
var shopApp = angular.module('shopApp', []);
shopApp.value("id", 0);

shopApp.controller('productsList', function ($scope) {
  var data = [
    {'name': 'Bitch 1',
     'price': '100.00'},
    {'name': 'Bitch 2',
     'price': '350.00'},
    {'name': 'Bitch 3',
     'price': '750.00'},
    {'name': 'Bitch 4',
     'price': '100.00'},
     {'name': 'Bitch 5',
     'price': '100.00'},
         {'name': 'Bitch 6',
     'price': '100.00'},
         {'name': 'Bitch 7',
     'price': '100.00'},
         {'name': 'Bitch 8',
     'price': '100.00'}
  ];
  
  $scope.columns = rowsize(data, 3);
  
    function rowsize(input, cols) {
        var arr = [];
        j = 0;
        for(i = 0; i < input.length; i++) {
           arr[j] = arr[j] || [];
           arr[j].push(input[i]);
           
            if ((i+1)%3==0) {
                j++;
            }
        }
        return arr;
    }

});