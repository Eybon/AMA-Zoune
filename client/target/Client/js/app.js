$(document).ready(function() {

    console.log("Ready to go!");

    var productListBlock = "#productsList";

    var id = 1;

    var updateProductsList = function() {
        console.log("Updating products list...");

        var data = [
            {'name': {'_':'Maillot 1'},
                'price': {'_':'100.00'}},
            {'name': {'_':'Maillot 2'},
                'price': {'_':'100.00'}},
            {'name': {'_':'Maillot 3'},
                'price': {'_':'100.00'}},
            {'name': {'_':'Maillot 4'},
                'price': {'_':'100.00'}},
            {'name': {'_':'Maillot 5'},
                'price': {'_':'100.00'}}
        ];

        $.soap({
            url: 'http://localhost:9763/services/Store/',
            namespaceURL: 'http://application.store'
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

                /*for (var result in results) {
                 console.log(results[result].description._);
                 }*/

                data = rowsize(data, 3);
                console.log(data);

                $(productListBlock).html(buildTemplateFromProductsList(data));
            },
            error: function (soapResponse) {
                console.error('that other server might be down... or the CORS...Using sample datas');

                data = rowsize(data, 3);
                console.log(data);

                $(productListBlock).html(buildTemplateFromProductsList(data));
            }
        });

    };

    updateProductsList();

    var rowsize = function(input, cols) {
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

    var buildTemplateFromProductsList = function(tab) {
        var tpl = "";

        for (var i = 0; i < tab.length; i++) {
            tpl += '<div class="content_grid" ng-repeat="row in columns">';
            for (var j = 0; j < tab[i].length; j++) {
                tpl += '<div class="col_1_of_3 span_1_of_3" ng-repeat="item in row">'
                    +'<div class="view view-first">'
                +'<div class="inner_content clearfix">'
                +'<div class="product_image">'
                +'<img src="images/pic1.jpg" class="img-responsive" alt=""/>'
                +'<div class="mask">'
                +'<div class="info buy-btn" data-product="'+ tab[i][j].name._ +'">BUY</div>'
                +'</div>'
                +'<div class="product_container">'
                +'<div class="cart-left">'
                +'<p class="title">'+ tab[i][j].name._ +'</p>'
                +'</div>'
                +'<div class="price">'+ tab[i][j].price._ +'</div>'
                +'<div class="clearfix"></div>'
                +'</div>'
                +'</div>'
                +'<div class="sale-box"><span class="on_sale title_shop">New</span></div>'
                +'</div>'
                +'</div>'
                +'</div>'
            }
            tpl += '<div  class="clearfix"></div>'
            + '</div>';
        }
        return tpl;
    }

    $("body").on("click", ".buy-btn", function(){
        var product = $(this).data("product");

        $.soap({
            url: 'http://localhost:9763/services/Store/',
            namespaceURL: 'http://application.store'
        });
        $.soap({
            method: 'addProductToBasket',
            data: {
                user : id,
                product : product,
                amount : 1
            },
            soap12: true,
            success: function (soapResponse) {
                // do stuff with soapResponse
                //console.log(soapResponse.toJSON()['#document']['ns:getListProductsResponse']['ns:return']);
                data = soapResponse.toJSON()['#document']['ns:addProductToBasketResponse']['ns:return'];
                console.log(data);

                displayBasket();
                $("#basket").click();
            },
            error: function (soapResponse) {
                console.error('that other server might be down... or the CORS...Using sample datas');
            }
        });

    });

    var updateBasket = function(price, items) {
        $(".cart_desc").html("$"+ price + "<br />" + items + " item(s)");
    }

    var products = [];

    var displayBasket = function() {

        $(".first-step").show();
        $(".second-step").hide();

        products = [];

        console.log("Updating basket...");
        $.soap({
            url: 'http://localhost:9763/services/Store/',
            namespaceURL: 'http://application.store'
        });
        $.soap({
            method: 'detailsBasket',
            data: {
                user : id
            },
            soap12: true,
            success: function (soapResponse) {
                // do stuff with soapResponse
                //console.log(soapResponse.toJSON()['#document']['ns:getListProductsResponse']['ns:return']);
                data = soapResponse.toJSON()['#document']['ns:detailsBasketResponse']['ns:return'];
                console.log(data);

                if (data) {

                    var total_cost = 0.0;
                    var total_nb_items = 0;

                    $(".modal_table").html("");

                    var render = '<tr class="a-product"><th>Product</th><th>Price</th><th>Quantity</th></tr>';
                    console.log(data.length);
                    if (data.length) {
                        for (var i = 0; i < data.length; i++) {
                            var block = data[i];
                            var token = (block["$"]["xsi:type"].split(":"))[0];

                            var item = block[token + ":product"];
                            var qty = block[token + ":quantity"];

                            products.push(item);

                            total_nb_items += parseInt(qty);
                            total_cost += 1.5 * item.price._ * qty;

                            //TODO update list of products in DOM
                            render += '<tr class="a-product" data-product="'+ item.name._ +'"><td>'+ item.name._ +'</td><td>'+ item.price._ +'</td><td><input class="product-input" value="'+ qty +'" type="number" max="999" min="0" data-product="' + item.name._ + '" /></td></tr>';
                            console.log(item.name._ + " reserved "+ qty +" times");
                        }
                    }
                    else
                    {
                        var token = (data["$"]["xsi:type"].split(":"))[0];

                        var item = data[token + ":product"];
                        var qty = data[token + ":quantity"];

                        products.push(item);

                        total_nb_items += parseInt(qty);
                        total_cost += 1.5 * item.price._ * qty;

                        //TODO update list of products in DOM
                        render += '<tr class="a-product" data-product="'+ item.name._ +'"><td>'+ item.name._ +'</td><td>'+ item.price._ +'</td><td><input class="product-input" value="'+ qty +'" type="number" max="999" min="0" data-product="' + item.name._ + '" /></td></tr>';
                        console.log(item.name._ + " reserved "+ qty +" times");
                    }

                    $(".modal-table").html(render);
                    $(".total-price").html("$" + total_cost);
                    $(".cart_desc").html("$"+ total_cost + "<br />" + total_nb_items + " item(s)");

                    //TODO Display list of products in DOM (popup)

                }

            },
            error: function (soapResponse) {
                console.error('that other server might be down... or the CORS...Using sample datas');
            }
        });
    }

    displayBasket();

    $("#basket").click(function() {
        $("#myBasket").modal("show");
        displayBasket();
    });

    $(".validate-basket-btn").click(function() {
        $(".first-step").hide();

        $.soap({
            url: 'http://localhost:9763/services/Store/',
            namespaceURL: 'http://application.store'
        });
        $.soap({
            method: 'validateBasket',
            data: {
                user : id
            },
            soap12: true,
            success: function (soapResponse) {

                data = soapResponse.toJSON()['#document']['ns:validateBasketResponse']['ns:return'];
                console.log(data);

                $(".second-step").show();

            },
            error: function (soapResponse) {
                console.error('that other server might be down... or the CORS...Using sample datas');

            }
        });

    });

    $(".pay-btn").click(function(){
        $.soap({
            url: 'http://localhost:9763/services/Store/',
            namespaceURL: 'http://application.store'
        });
        $.soap({
            method: 'payOrder',
            data: {
                user : id,
                ccnumber : $(".cc_number").val(),
                limitmonth : $(".limit_month").val(),
                limityear: $(".limit_year").val(),
                owner: $(".owner").val(),
                CCV: $(".ccv").val()
            },
            soap12: true,
            success: function (soapResponse) {
                // do stuff with soapResponse
                //console.log(soapResponse.toJSON()['#document']['ns:getListProductsResponse']['ns:return']);
                data = soapResponse.toJSON()['#document']['ns:payOrderResponse']['ns:return'];
                console.log(data);


            },
            error: function (soapResponse) {
                console.error('that other server might be down... or the CORS...Using sample datas');

            }
        });
    });


    $("body").on("input", ".product-input", function() {
        var qty = $(this).val();
        var product = $(this).data("product");

        console.log("Edited quantity of "+ qty +" for product "+ product);

        $.soap({
            url: 'http://localhost:9763/services/Store/',
            namespaceURL: 'http://application.store'
        });
        $.soap({
            method: 'addProductToBasket',
            data: {
                user : id,
                product : product,
                amount : qty
            },
            soap12: true,
            success: function (soapResponse) {
                // do stuff with soapResponse
                //console.log(soapResponse.toJSON()['#document']['ns:getListProductsResponse']['ns:return']);
                data = soapResponse.toJSON()['#document']['ns:addProductToBasketResponse']['ns:return'];
                console.log(data);

                if (qty <= 0) {
                    $(this).closest('tr').remove();
                }

                displayBasket();
            },
            error: function (soapResponse) {
                console.error('that other server might be down... or the CORS...Using sample datas');

            }
        });

    });

});

