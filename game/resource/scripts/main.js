
var keyboard = Java.type("org.lwjgl.input.Keyboard");

var b = game.newBehavior(function(behavior, target, parameters) {
	o.on('tick', function() {
		if (keyboard.isKeyDown(keyboard.KEY_LEFT)) {
			var position = this.get(core.position);
			position.x -= 0.3;
		}
		if (keyboard.isKeyDown(keyboard.KEY_RIGHT)) {
			var position = this.get(core.position);
			position.x += 0.3;
		}
		if (keyboard.isKeyDown(keyboard.KEY_UP)) {
			var position = this.get(core.position);
			position.y -= 0.3;
		}
		if (keyboard.isKeyDown(keyboard.KEY_DOWN)) {
			var position = this.get(core.position);
			position.y += 0.3;
		}
	});
});

var scene = game.newScene();
game.setCurrentScene(scene);

var o = scene.newGameObject();
b.apply(o);
core.rectangle.apply(o);
core.position.apply(o, {x: 5});

