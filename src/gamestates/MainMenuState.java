package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import tilemap.Background;
import ui.MenuButton;
import utils.Constants;

public class MainMenuState extends GameState implements StateMethods {
	private Background bg;
	private MenuButton[] buttons;
	private GameStateManager gsm;

	public MainMenuState(GameStateManager gsm) {
		this.gsm = gsm;
		buttons = new MenuButton[2];

		bg = new Background(Constants.Backgrounds.MAIN_MENU);

		loadButtons();
	}

	private boolean isIn(MouseEvent e, MenuButton mb) {
		return mb.getBounds().contains(e.getX(), e.getY());
	}

	private void loadButtons() {
		buttons[0] = new MenuButton(Constants.Panel.WIDTH / 2, 340, 0, EnumState.PLAYING);
		buttons[1] = new MenuButton(Constants.Panel.WIDTH / 2, 400, 1, EnumState.QUIT);
	}

	@Override
	public void update() {
		//bg.update();
		for (MenuButton mb : buttons) {
			mb.update();
		}
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics g) {
		bg.draw(g);
		for (MenuButton mb : buttons) {
			mb.draw(g);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (MenuButton mb : buttons) {
			if (isIn(e, mb)) {
				mb.setMousePressed(true);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for (MenuButton mb : buttons) {
			if (isIn(e, mb)) {
				if (mb.isMousePressed()) {
					mb.applyEnumstate();
				}
				break;
			}
		}
		resetButton();
	}

	private void resetButton() {
		for (MenuButton mb : buttons) {
			mb.resetBools();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ENTER) {
			EnumState.state = EnumState.PLAYING;
		}
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
	}
}
