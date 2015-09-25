/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var escena, camara, renderer, controles; 
var keyboard = new THREEx.KeyboardState(); 
var clock = new Three.Clock();
var esfera; 

init(); 

function init () {
    
    escena= new THREE.Scene(); 
    
    var SCREEN_WIDTH = window.innerWidth, SCREEN_HEIGHT = window.innerHeight;	
	
	var VIEW_ANGLE = 45, ASPECT = SCREEN_WIDTH / SCREEN_HEIGHT, NEAR = 0.1, FAR = 20000;
        
        camara = new THREE.PerspectiveCamera( VIEW_ANGLE, ASPECT, NEAR, FAR);
	// add the camera to the scene
		escena.add(camara);
		camara.position.set(20,50,100);
		camara.lookAt(escena.position);	
        //agregamos la orbita o elipse y sus materiales 
                
        var material = new THREE.LineBasicMaterial({color:0x2F4F4F, opacity:1});
	var ellipse = new THREE.EllipseCurve(0, 0, 30, 20, 0, 2 * Math.PI, false);
	var ellipsePath = new THREE.CurvePath();
		ellipsePath.add(ellipse);

	var ellipseGeometry = ellipsePath.createPointsGeometry(100);
		ellipseGeometry.computeTangents();
	
        var line = new THREE.Line(ellipseGeometry, material);
		line.rotation.set( Math.PI/2, 0, 0 )
	escena.add( line );
        
    //creacion del render
        
    if ( Detector.webgl )
		renderer = new THREE.WebGLRenderer( {antialias:true} );
	else
		renderer = new THREE.CanvasRenderer(); 

	renderer.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        
        THREEx.WindowResize(renderer, camara);
        
        var light = new THREE.PointLight(0xffffff);
		light.position.set(0,0,100);
	escena.add(light);
	var ambientLight = new THREE.AmbientLight(0x111111);
}



