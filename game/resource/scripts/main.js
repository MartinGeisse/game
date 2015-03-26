
var keyboard = Java.type("org.lwjgl.input.Keyboard");
var resources = Java.type("game.engine.resource.Resources");

var moveByArrowKeys = game.newBehavior(function() {
	this.on('tick', function() {
		if (keyboard.isKeyDown(keyboard.KEY_LEFT)) {
			var position = this.get(core.position);
			position.x -= 0.3;
			if (this.get(core.leftRight) != null) {
				this.set(core.leftRight, 'left');
			}
		}
		if (keyboard.isKeyDown(keyboard.KEY_RIGHT)) {
			var position = this.get(core.position);
			position.x += 0.3;
			if (this.get(core.leftRight) != null) {
				this.set(core.leftRight, 'right');
			}
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
core.position.apply(o, {x: 5});
moveByArrowKeys.apply(o);
core.rectangle.apply(o);
core.leftRight.apply(o);
core.sprite.apply(o, new (Java.type('game.engine.gfx.Sprite'))(resources.getTexture("sprites/player-left.png"), 0.6, 0.6, 0.6, 0.6));
