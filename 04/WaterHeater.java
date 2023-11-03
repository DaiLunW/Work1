import java.util.Timer;  
import java.util.TimerTask;

public class WaterHeater {  
    private static final int WATER_TEMPERATURE_THRESHOLD = 20;  
    private static final int MAX_TEMPERATURE_THRESHOLD = 100;  
    private static final int SHUTDOWN_TIME = 23 * 60 * 60 + 11 * 60; // 晚上11点整  
    private static final int WAKEUP_TIME = 7 * 60 * 60; // 早上7点整

    public static void main(String[] args) {  
        WaterHeater waterHeater = new WaterHeater();  
        waterHeater.startHeating();  
    }

    public void startHeating() {  
        Timer timer = new Timer();  
        TimerTask task = new TimerTask() {  
            @Override  
            public void run() {  
                handleWaterHeater();  
            }  
        };

        timer.schedule(task, SHUTDOWN_TIME, WAKEUP_TIME);  
    }

    private void handleWaterHeater() {  
        boolean hasWater = true; // 假设水量传感器检测到有水  
        boolean isBelow20Degrees = true; // 假设温度传感器检测到低于 20°

        if (hasWater && isBelow20Degrees) {  
            // 打开继电器电源，开始烧水  
            System.out.println("Start heating water");  
        } else {  
            System.out.println("No water, not heating");  
        }

        // 模拟水箱烧坏的情况  
        if (hasWater && isBelow20Degrees) {  
            System.out.println("Water heater broken, need repair");  
        }  
    }  
}