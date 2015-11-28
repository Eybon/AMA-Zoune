/**
 * Maxime BERGEON
 * Antoine Forgerou
 * 18/11/2015
 */
var shopApp = angular.module('shopApp', []);
shopApp.value("id", 0);

shopApp.controller('productsList', function ($scope) {

    var data;

    $.soap({
        url: 'http://localhost:9763/services/Store/',
        namespaceURL: 'http://store'
    });
    $.soap({
        method: 'getListProducts',
        data: {},
        soap12: true,
        success: function (soapResponse) {
            // do stuff with soapResponse
            //console.log(soapResponse.toJSON()['#document']['ns:getListProductsResponse']['ns:return']);
            data = soapResponse.toJSON()['#document']['ns:getListProductsResponse']['ns:return'];
            console.log(data);
            $scope.$apply(function() {
                $scope.columns = rowsize(data, 3);
            });

            /*for (var result in results) {
                console.log(results[result].description._);
            }*/
        },
        error: function (soapResponse) {
            console.error('that other server might be down... or the CORS...');
        }
    });
  
    //$scope.columns = rowsize(data, 3);
  
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

shopApp.controller('basket', function($scope) {

    var data;

    $.soap({
        url: 'http://localhost:9763/services/Store/',
        namespaceURL: 'http://store'
    });
    $.soap({
        method: 'detailsBasket',
        data: {
            user: user
        },
        soap12: true,
        success: function (soapResponse) {
            // do stuff with soapResponse
            data = soapResponse.toJSON()['#document']['ns:detailsBasketResponse']['ns:return'];

            $scope.$apply();
        },
        error: function (soapResponse) {
            console.error('that other server might be down... or the CORS...');
        }
    });

});