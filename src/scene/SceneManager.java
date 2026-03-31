package scene;

import java.awt.Graphics;

/**
 * 场景管理器
 * 负责管理游戏中所有场景的切换和生命周期
 *
 * 使用场景：
 * - 主菜单 → 游戏关卡 → 暂停界面 → 游戏结束
 * - 不同关卡之间的切换
 */
public class SceneManager {

    // 当前正在运行的场景
    private Scene currentScene;

    /**
     * 设置新的当前场景
     *
     * 执行流程：
     * 1. 如果已有场景存在，先调用其 dispose() 方法释放资源
     * 2. 将新场景设置为当前场景
     * 3. 调用新场景的 init() 方法进行初始化
     *
     * @param scene 要切换到的新场景
     */
    public void setScene(Scene scene) {

        // 如果当前已有场景，先清理旧场景的资源
        if(currentScene != null) {
            currentScene.dispose();  // 释放旧场景的资源（如图片、音效等）
        }

        // 切换到新场景
        currentScene = scene;

        // 初始化新场景（加载资源、创建实体等）
        currentScene.init();
    }

    /**
     * 更新当前场景的逻辑状态
     *
     * 在游戏循环中被调用（通常每秒 60 次）
     * 处理：
     * - 输入检测
     * - 物理计算
     * - AI 行为
     * - 碰撞检测
     */
    public void update() {

        // 确保当前场景存在才更新
        if(currentScene != null) {
            currentScene.update();  // 调用当前场景的更新逻辑
        }
    }

    /**
     * 渲染当前场景的画面
     *
     * 在游戏循环中被调用（通常每秒 60 次）
     * 负责绘制：
     * - 背景
     * - 角色
     * - UI 界面
     * - 特效等
     *
     * @param g Graphics 对象，用于绘制操作
     */
    public void render(Graphics g) {

        // 确保当前场景存在才渲染
        if(currentScene != null) {
            currentScene.render(g);  // 调用当前场景的渲染方法
        }
    }
}
