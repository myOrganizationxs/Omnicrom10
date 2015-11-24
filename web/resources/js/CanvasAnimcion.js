/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
/******************************* variables *******************/
			//Preparamos el render     
        //obtener datos para crear los nodos
        
       
        var resultado = new Array();
        resultado[0] = new Array();
        resultado[0][0]="nombre " + 0;
        resultado[0][1]="id " + 0;
          resultado[1] = new Array();
        resultado[1][0]="nombre " + 1;
        resultado[1][1]="id " + 1;
          resultado[2] = new Array();
        resultado[2][0]="nombre " + 2;
        resultado[2][1]="id " + 2;
        var NodosHijos= document.getElementById("NodosHijos").value;//resultado.length;
        var NodosHijos1= JSON.parse(NodosHijos);
        //nodo centro y elipse        
        var line;
        var ellipseGeometry;
        var ellipsePath;
        var ellipse;
        var material;
         var recargars=0;
        var nodo;
        var nEsferas=0;                	
        var controls;
        var central;
        var central_geometry;
        var central_material;
        var flag;
        var distanciaSuma;
        var flags, sphere, sphereGeometry, sphereMaterial;       
        var variableAnimacion = true;
        //variable tiempo para aumentar
        var tiempo=0;
        var tiempozoom=200;
        var variablevelocidad=.02;
        var arregloDeSuma;
        var ddomEvents;
        
        function creaEsferas()
        {            
            nEsferas=0;
            nEsferas=NodosHijos1.length-1;  
            
        } 
        
        var Render=new THREE.WebGLRenderer();		
        ////El escenario
	var Escenario=new THREE.Scene();
        var keyboard = new THREEx.KeyboardState();
        
	// la Figura 
        var t=0;
	var t6=31;
        var anima;
        
	flag = true;
        flags = new Array(nEsferas);
	sphere=new Array(nEsferas);
        sphereGeometry= new Array(nEsferas);
        sphereMaterial= new Array(nEsferas);      
        arregloDeSuma=new Array(nEsferas);
        ddomEvents= new Array(nEsferas);
        
	var Ancho=window.innerWidth-50;
	var Alto=window.innerHeight-50;
			
	var Angulo = 45;	
	var Aspecto=Ancho/Alto;
	var cerca=0.1;
	var lejos=10000;
        
	//La cámara
	var Camara;
		
			
			/******************************* inicio *******************/
	function inicio()
        {    
            
            Camara = new THREE.PerspectiveCamera(Angulo,Aspecto,cerca,lejos);
            THREEx.WindowResize(Render,Camara);
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
        
        material = new THREE.LineBasicMaterial({color:0xffffff, opacity:1});
	ellipse = new THREE.EllipseCurve(0, 0, 30, 20, 0, 2 * Math.PI, false);
	ellipsePath = new THREE.CurvePath();
		ellipsePath.add(ellipse);

	ellipseGeometry = ellipsePath.createPointsGeometry(100);
		ellipseGeometry.computeTangents();
	line = new THREE.Line(ellipseGeometry, material);
		line.rotation.set( Math.PI/2, 0, 0 )
	Escenario.add( line );
        
        central_geometry = new THREE.SphereGeometry( 2, 32, 32 );
        central_material = new THREE.MeshPhongMaterial( { color: 0x87CEEB  } );
	central = new THREE.Mesh( central_geometry, central_material );
	central.name = NodosHijos1[0];
        Escenario.add(central);
        
        var domEvents= new THREEx.DomEvents(Camara, Render.domElement);
	// DOM event para la central
	domEvents.addEventListener(central, 'mouseover', function(event){flag = false;}, false)

	domEvents.addEventListener(central, 'mouseout', function(event){
		// animate(id);
		flag = true;
            }, false)
                

	domEvents.addEventListener(central, 'click', function(event){
            
            //alert(central.name);
            //window["cosa" + i] = "alguna otra cosa";
            //alert(cosa1);
           
            RemoverSphere();
            crear_ellipse();
            creaEsferas();
            if(nEsferas!==0)
            {    
            dibujaEsferas();
            }
            animacion();
            
            
                //alert(NodosHijos+"  "+nodo+"  "+nEsferas);
		// window.location = 'https://www.facebook.com';
	}, false)
        
         
    }
    function dibujaEsferas(){
        
        arregloDeSuma=new Array(nEsferas);
        var variable1=nEsferas;
        var distanciaEntrenodos=64/(variable1);
        var distanciaSuma=0;      
        //alert(nEsferas);
        for (var i=1; i<=nEsferas; i++) 
        {
        
            sphereGeometry[i] = new THREE.SphereGeometry( 2, 32, 32 ); 
            sphereMaterial[i] = new THREE.MeshPhongMaterial( { color: 0x87CEEB } );
            sphere[i] = new THREE.Mesh(sphereGeometry[i], sphereMaterial[i]);
            sphere[i].position.x = 30;
            sphere[i].name=NodosHijos1[i];
            Escenario.add(sphere[i]);            
            arregloDeSuma[i]=distanciaSuma;
            distanciaSuma=distanciaSuma+distanciaEntrenodos;
            ddomEvents[i] = new THREEx.DomEvents(Camara, Render.domElement);
           /* ddomEvents[i].addEventListener(sphere[i], 'mouseover',
            function(sphere[i]){
                alert(this);
                console.log(this);
            }, false)*/
            ddomEvents[i].bind(sphere[i], 'mouseover', function(object3d){ console.log(object3d.target.name) });
        }       	// fin de DOM para cental
    }     
          
         
          
        function animacion1(){     
            
            //var delta = Math.random() * (0.06 - 0.02) + 0.02;  
            if(tiempo<tiempozoom){
            Camara.scale.z+=variablevelocidad;  
            
            }
            tiempo++;           
        }
                
	function animacion(){
            
            
            if (flag==true) {
                
               
                if(nEsferas!==0)
            {    

                for (var i=1; i<=nEsferas; i++) 
                
                {
                    sphere[i].position.x = Math.sin(arregloDeSuma[i]*0.1)*30;
                    sphere[i].position.z = Math.cos(arregloDeSuma[i]*0.1)*20;
                    //console.log(sphere[i]);
                    arregloDeSuma[i]-=Math.PI/180*2;
                    //console.log("quiero ver el valor " + arregloDeSuma[i]);
                    //[i];
                }}
    };
        
	requestAnimationFrame(animacion);
        render_modelo();
        animacion1();
	}
        
	function render_modelo(){
                    controls.update();
                    Render.render(Escenario,Camara);
	}
	/**************************llamado a las funciones ******************/
$(document).ready(function(){	
            primero();
        });
        
        function primero(){
            
            inicio();
            creaEsferas();
            if(nEsferas!==0)
            {    
            dibujaEsferas();
            }
            animacion();
        }
        
        function recarga(){
            if(recargars===1){
            crear_ellipse();
            creaEsferas();
            if(nEsferas!==0)
            {    
            dibujaEsferas();
            }
          //  animacion();
            }
        }
        function RemoverSphere()
        {
            
            //alert("NUmero de esferas"+nEsferas);
            for (var i=0; i<=nEsferas; i++) 
            {
                Escenario.remove(sphere[i]);
            }
            Escenario.remove(line);
            Escenario.remove(central);
            variableAnimacion=false;
            Camara.scale.z=1;
            
            tiempo=0;
            tiempozoom:200;
            recargars=1;
            
        }
        
        function removercentro()
        {
            Escenario.remove(central);
        }
        
