/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
/******************************* variables *******************/
			//Preparamos el render     
        var NodosHijos= document.getElementById("NodosHijos").value;
        var Render=new THREE.WebGLRenderer();		
        ////El escenario
	var Escenario=new THREE.Scene();
        var keyboard = new THREEx.KeyboardState();
	
        
	// la Figura 
        var t=0;
	var t6=31;
        var anima;
        
	var flag = true;
	var sphere;
	var controls;
	var Ancho=window.innerWidth-50;
	var Alto=window.innerHeight-50;
			
	var Angulo = 45;	
	var Aspecto=Ancho/Alto;
	var cerca=0.1;
	var lejos=10000;
        
	//La cámara
	var Camara=new THREE.PerspectiveCamera(Angulo,Aspecto,cerca,lejos);
        THREEx.WindowResize(Render,Camara);
			
			
			
			/******************************* inicio *******************/
	function inicio(){
        
        
	//Tamaño del render(resultado)
	Render.setSize(Ancho,Alto);
	//Se agrega el render al documento html
	document.getElementById('render').appendChild(Render.domElement);
        var SCREEN_WIDTH = window.innerWidth, SCREEN_HEIGHT = window.innerHeight;	
	// camera attributes
	var VIEW_ANGLE = 45, ASPECT = SCREEN_WIDTH / SCREEN_HEIGHT, NEAR = 0.1, FAR = 20000;
	//Acercamos la cámara en z es profundidad para ver el punto
	Camara.position.set(100,100,300);
	//agregando la cámara al escenario
        Camara.lookAt(Escenario.position);
	Escenario.add(Camara);
	crear_ellipse();
	
        // agregamos todo el escenario y la cámara al render
	controls=new THREE.OrbitControls(Camara,Render.domElement);
	}
        
        var light = new THREE.PointLight(0xffffff);
	light.position.set(20,0,85);
	Escenario.add(light);
	var ambientLight = new THREE.AmbientLight(0x111111);
	
        function crear_ellipse(){	
	// Geometría
        
        var material = new THREE.LineBasicMaterial({color:0xffffff, opacity:1});
	var ellipse = new THREE.EllipseCurve(0, 0, 30, 20, 0, 2 * Math.PI, false);
	var ellipsePath = new THREE.CurvePath();
		ellipsePath.add(ellipse);

	var ellipseGeometry = ellipsePath.createPointsGeometry(100);
		ellipseGeometry.computeTangents();
	var line = new THREE.Line(ellipseGeometry, material);
		line.rotation.set( Math.PI/2, 0, 0 )
	Escenario.add( line );
        
        var central_geometry = new THREE.SphereGeometry( 2, 32, 32 );
	var central_material = new THREE.MeshPhongMaterial( { color: 0x87CEEB  } );
	var central = new THREE.Mesh( central_geometry, central_material );
	Escenario.add( central );
        
        var sphereGeometry = new THREE.SphereGeometry( 2, 32, 32 ); 
	var sphereMaterial = new THREE.MeshPhongMaterial( { color: 0x87CEEB } );
		sphere = new THREE.Mesh(sphereGeometry, sphereMaterial);
		sphere.position.x = 30;
	Escenario.add(sphere);
        
        var sub6_geometry = new THREE.SphereGeometry( 2, 20, 20 )
	var sub6_material = new THREE.MeshPhongMaterial( { color: 0x87CEEB } ); //amarilla
		sub6 = new THREE.Mesh( sub6_geometry, sub6_material );
		sub6.position.x = 30;
	Escenario.add(sub6);

        var domEvents	= new THREEx.DomEvents(Camara, Render.domElement)
	
	domEvents.addEventListener(sphere, 'mouseover', function(event){
		flag = false;
            PF('dlg4').show();
                     
                   
        }, false)

	domEvents.addEventListener(sphere, 'mouseout', function(event){
		// animate(id);
		flag = true;
		sphere.scale.x = 1;
		sphere.scale.y = 1;
		sphere.scale.z = 1;
                
                
                }, false)
                
	domEvents.addEventListener(sphere, 'click', function(event){
		sphere.position.x = 0;
		sphere.position.y = 0;
		sphere.position.z = 0;
		sphere.scale.x = 16;
		sphere.scale.y = 16;
		sphere.scale.z = 16;
                
                
		
                }, false)
            
	// DOM event para la central
	domEvents.addEventListener(central, 'mouseover', function(event){flag = false;}, false)

	domEvents.addEventListener(central, 'mouseout', function(event){
		// animate(id);
		flag = true;
		central.scale.x = 1;
		central.scale.y = 1;
		central.scale.z = 1;}, false)
                

	domEvents.addEventListener(central, 'click', function(event){
		central.position.x = 0;
		central.position.y = 0;
		central.position.z = 0;
		central.scale.x = 13;
		central.scale.y = 13;
		central.scale.z = 13;
		PF('dlg5').show();
                alert(NodosHijos);
		// window.location = 'https://www.facebook.com';
	}, false)
	// fin de DOM para cental
        
        
        
        
	}
        /*
        var LA= Camara.scale.z+=0.1; 
       
       function animacion1(){
        
           if(LA>=0.9){
          cancelAnimationFrame(animacion1);
          }
           else{
          Camara.scale.z+=0.1;
          Camara=requestAnimationFrame(animacion1);   
          }
             
       }
       
        */
        function animacion2(){
            domEvents.addEventListener(central, 'click', function(event){
		central.position.x = 0;
		central.position.y = 0;
		central.position.z = 0;
		central.scale.x = 13;
		central.scale.y = 13;
		central.scale.z = 13;
		PF('dlg5').show();
		// window.location = 'https://www.facebook.com';
	}, false)
	// fin de DOM para cental
        
        
        
        
	}
            
        
        
        var tiempo=0;
        function animacion1(){
            console.log(tiempo);
            var delta = Math.random() * (0.06 - 0.02) + 0.02;
    
            if(tiempo<200){
            Camara.scale.z+=0.01;
            
            }
            tiempo++;
            
        }
        
        
	function animacion(){
            
            
            if (flag==true) {
		sub6.position.x = Math.sin(t6*0.1)*30;
		sub6.position.z = Math.cos(t6*0.1)*20;
		t6-=Math.PI/180*2;
		
		sphere.position.x = Math.sin(t*0.1)*30;
		sphere.position.z = Math.cos(t*0.1)*20;
		t-=Math.PI/180*2;
    };
        /*
        tiempo=0.001;
        distancia=100;
        recorrido=distancia*tiempo;
        
            if(keyboard.pressed("w")){
            Camara.scale.z+=recorrido;
        }
        
            if(keyboard.pressed("s")){
            Camara.scale.z-=recorrido;
        }
          */  
	requestAnimationFrame(animacion);
        render_modelo();
        animacion1();
        animacion2();
        
       
        
        
              
        
            
        
	}
	function render_modelo(){
	controls.update();
	Render.render(Escenario,Camara);
	}
	/**************************llamado a las funciones ******************/
			
	inicio();
	animacion();
