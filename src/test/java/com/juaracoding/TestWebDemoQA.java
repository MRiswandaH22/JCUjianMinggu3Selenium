package com.juaracoding;

import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.SortedMap;
import java.util.concurrent.TimeUnit;

public class TestWebDemoQA {

    WebDriver driver;

    String pathChromeDriver = "C:\\juaracoding\\chromedriver.exe";

    @BeforeClass
    public void setUp(){

        System.setProperty("webdriver.chrome.driver", pathChromeDriver);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test(priority = 1)
    public void testUrl(){
        //step action
        driver.get("https://shop.demoqa.com/my-account/");
        System.out.println("Get URL Login Shop DemoQA");

        driver.manage().window().maximize();
        System.out.println("Windows Maximaze");

        //step action

        String judulSesi = driver.findElement(By.xpath("//*[@id='customer_login']/div[1]/h2")).getText();
        System.out.println("Judul Halaman : "+judulSesi);

        //step verify
        Assert.assertEquals(judulSesi, "LOGIN");
    }


    @Test(priority = 2)
    public void testLogin(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window,scroll(0,450)");

        delay(3);

        //step action

        WebElement element1 = driver.findElement(By.id("username")); element1.sendKeys("riswanda22");
        System.out.println("Masukan Username : "+element1.getAttribute("value"));

        WebElement element2 = driver.findElement(By.id("password")); element2.sendKeys("Ciwan220399");
        System.out.println("Masukan Password : "+element2.getAttribute("value"));


        driver.findElement(By.xpath("//*[@id='customer_login']/div[1]/form/p[3]/button")).click();
        System.out.println("Button Login Clicked");

        //step verify
        String txtUsername = driver.findElement(By.xpath("//*[@id='post-8']/div/div/div/p[1]/strong[1]")).getText();
        Assert.assertTrue(txtUsername.contains("riswanda22"));
        System.out.println("Berhasil Login");


    }

    @Test(priority = 3)
    public void orderBarang(){

        //step action
        driver.findElement(By.linkText("ToolsQA Demo Site")).click();
        System.out.println("Masuk ke Menu Utama");
        delay(3);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window,scroll(0,1000)");

        String judulMenu = driver.findElement(By.xpath("//*[@id='noo-site']/div[2]/div[3]/div/div[2]/div/div/div/div[1]/h3/span")).getText();
        System.out.println("Masuk ke Menu : "+judulMenu);

        driver.findElement(By.xpath("//*[@id='noo-site']/div[2]/div[3]/div/div[2]/div/div/div/div[2]/div[2]/div[1]/div/h3")).click();

        //step verify
        Assert.assertEquals(judulMenu,"FOR LADIES");
    }

    @Test(priority = 4)
    public void detailOrder(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window,scroll(0,550)");
        delay(3);
        //step action
        String namaBaju = driver.findElement(By.xpath("//*[@id='product-1497']/div[1]/div[2]/h1")).getText();
        System.out.println("Nama Baju Yang Dipilih : "+namaBaju);

        WebElement selectColor = driver.findElement(By.id("pa_color"));
        Select select = new Select(selectColor);
        select.selectByIndex(1);
        System.out.println("Selected color : "+selectColor.getAttribute("value"));

        WebElement selectSize = driver.findElement(By.id("pa_size"));
        Select select2 = new Select(selectSize);
        select2.selectByIndex(2);
        System.out.println("Selected Size : "+selectSize.getAttribute("value"));

        //step verify
        Assert.assertEquals(namaBaju,"PINK DROP SHOULDER OVERSIZED T SHIRT");
        Assert.assertEquals(selectColor.getAttribute("value"),"pink");
        Assert.assertEquals(selectSize.getAttribute("value"), "37");

        driver.findElement(By.xpath("//*[@id='product-1497']/div[1]/div[2]/form/div/div[2]/button")).click();
        js.executeScript("window,scroll(0,-500)");
        delay(2);

        //step action
        String txtAddToCart = driver.findElement(By.className("woocommerce-message")).getText();
        System.out.println(txtAddToCart);

        //step verify
        Assert.assertTrue(txtAddToCart.contains("added to your cart"));

    }

    @Test(priority = 5)
    public void testCart(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.findElement(By.className("cart-button")).click();
        System.out.println("Cart Opened");
        js.executeScript("window,scroll(0,600)");
        delay(3);

        //step action
        String txtCart = driver.findElement(By.className("page-title")).getText();

        //step verify
        Assert.assertEquals(txtCart,"CART");

    }

    @AfterClass
    public void quitBrowser(){
        delay(3);
        driver.quit();
    }

    static void delay(int detik){
        try {
            Thread.sleep(detik*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
