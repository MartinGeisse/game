
var b = game.newBehavior(function(o) {
});

var scene = game.newScene();
game.setCurrentScene(scene);

var o = scene.newGameObject();
b.apply(o);
core.rectangle.apply(o);
core.position.apply(o, {x: 100});

