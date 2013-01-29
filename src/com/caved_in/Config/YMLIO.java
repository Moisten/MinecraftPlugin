package com.caved_in.Config;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
 
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
 
public class YMLIO {
    private FileConfiguration yml=new YamlConfiguration();
    private File file=null;
    private boolean force=false;
 
    public YMLIO(final File file) throws FileNotFoundException, IOException, InvalidConfigurationException {
        this.file=file;
        if(file.exists()){
            this.yml.load(this.file);
        }
    }
 
    public boolean contains(final String path) {
        return this.hasPath(path, "");
    }
 
    public boolean get(final String path, final boolean def) {
        boolean obj=this.yml.getBoolean(path, def);
        if(this.force){
            obj=def;
        }
        this.set(path, obj);
        return obj;
    }
 
    public double get(final String path, final double def) {
        double obj=this.yml.getDouble(path, def);
        if(this.force){
            obj=def;
        }
        this.set(path, obj);
        return obj;
    }
 
    public int get(final String path, final int def) {
        int obj=this.yml.getInt(path, def);
        if(this.force){
            obj=def;
        }
        this.set(path, obj);
        return obj;
    }
 
    public ItemStack get(final String path, final ItemStack def) {
        ItemStack obj=this.yml.getItemStack(path, def);
        if(this.force){
            obj=def;
        }
        this.set(path, obj);
        return obj;
    }
 
    public long get(final String path, final long def) {
        long obj=this.yml.getLong(path, def);
        if(this.force){
            obj=def;
        }
        this.set(path, obj);
        return obj;
    }
 
    public Object get(final String path, final Object def) {
        Object obj=this.yml.get(path, def);
        if(this.force){
            obj=def;
        }
        this.set(path, obj);
        return obj;
    }
 
    public OfflinePlayer get(final String path, final OfflinePlayer def) {
        OfflinePlayer obj=this.yml.getOfflinePlayer(path, def);
        if(this.force){
            obj=def;
        }
        this.set(path, obj);
        return obj;
    }
 
    public String get(final String path, final String def) {
        String obj=this.yml.getString(path, def);
        if(this.force){
            obj=def;
        }
        this.set(path, obj);
        return obj;
    }
 
    public Map<String, Object> getAsMap(final String path) {
        final Map<String, Object> map=new HashMap<String, Object>();
        if(this.yml.contains(path)&&this.yml.isConfigurationSection(path)){
            final Set<String> keys=this.yml.getConfigurationSection(path).getKeys(false);
            if(keys.size()>0){
                final Object[] key=keys.toArray();
                for(final Object element:key){
                    map.put(path+"."+(String) element, this.getObject(path+"."+(String) element));
                }
            }
        }
        return map;
    }
 
    public List<String> getAsPathList(final String path) {
        final List<String> list=new ArrayList<String>();
        if(this.yml.contains(path)&&this.yml.isConfigurationSection(path)){
            final Set<String> keys=this.yml.getConfigurationSection(path).getKeys(false);
            if(keys.size()>0){
                final Object[] key=keys.toArray();
                for(final Object element:key){
                    list.add(path+"."+(String) element);
                }
            }
        }
        return list;
    }
 
    public List<Object> getAsValueList(final String path) {
        final List<Object> list=new ArrayList<Object>();
        if(this.yml.contains(path)&&this.yml.isConfigurationSection(path)){
            final Set<String> keys=this.yml.getConfigurationSection(path).getKeys(false);
            if(keys.size()>0){
                final Object[] key=keys.toArray();
                for(final Object element:key){
                    list.add(this.getObject(path+"."+(String) element));
                }
            }
        }
        return list;
    }
 
    public boolean getBoolean(final String path) {
        return this.yml.getBoolean(path);
    }
 
    public List<Boolean> getBooleanList(final String path) {
        return this.yml.getBooleanList(path);
    }
 
    public List<Boolean> getBooleanList(final String path, final List<Boolean> def) {
        List<Boolean> obj=def;
        if(this.yml.contains(path)){
            obj=this.getBooleanList(path);
        }
        if(this.force){
            obj=def;
        }
        this.set(path, obj);
        return obj;
    }
 
    public List<Byte> getByteList(final String path) {
        return this.yml.getByteList(path);
    }
 
    public List<Byte> getByteList(final String path, final List<Byte> def) {
        List<Byte> obj=def;
        if(this.yml.contains(path)){
            obj=this.getByteList(path);
        }
        if(this.force){
            obj=def;
        }
        this.set(path, obj);
        return obj;
    }
 
    public List<Character> getCharacterList(final String path) {
        return this.yml.getCharacterList(path);
    }
 
    public List<Character> getCharacterList(final String path, final List<Character> def) {
        List<Character> obj=def;
        if(this.yml.contains(path)){
            obj=this.getCharacterList(path);
        }
        if(this.force){
            obj=def;
        }
        this.set(path, obj);
        return obj;
    }
 
    public double getDouble(final String path) {
        return this.yml.getDouble(path);
    }
 
    public List<Double> getDoubleList(final String path) {
        return this.yml.getDoubleList(path);
    }
 
    public List<Double> getDoubleList(final String path, final List<Double> def) {
        List<Double> obj=def;
        if(this.yml.contains(path)){
            obj=this.getDoubleList(path);
        }
        if(this.force){
            obj=def;
        }
        this.set(path, obj);
        return obj;
    }
 
    public File getFile() {
        return this.file;
    }
 
    public FileConfiguration getFileConfiguration() {
        return this.yml;
    }
 
    public List<Float> getFloatList(final String path) {
        return this.yml.getFloatList(path);
    }
 
    public List<Float> getFloatList(final String path, final List<Float> def) {
        List<Float> obj=def;
        if(this.yml.contains(path)){
            obj=this.getFloatList(path);
        }
        if(this.force){
            obj=def;
        }
        this.set(path, obj);
        return obj;
    }
 
    public String getHeader() {
        return this.yml.options().header();
    }
 
    public String getHeader(final String def) {
        String str=this.getHeader();
        if(str.isEmpty()){
            str=def;
        }
        if(this.force){
            str=def;
        }
        this.setHeader(str);
        return str;
    }
 
    public int getInt(final String path) {
        return this.yml.getInt(path);
    }
 
    public List<Integer> getIntegerList(final String path) {
        return this.yml.getIntegerList(path);
    }
 
    public List<Integer> getIntegerList(final String path, final List<Integer> def) {
        List<Integer> obj=def;
        if(this.yml.contains(path)){
            obj=this.getIntegerList(path);
        }
        if(this.force){
            obj=def;
        }
        this.set(path, obj);
        return obj;
    }
 
    public ItemStack getItemStack(final String path) {
        return this.yml.getItemStack(path);
    }
 
    public List<?> getList(final String path) {
        return this.yml.getList(path);
    }
 
    public List<?> getList(final String path, final List<?> def) {
        List<?> obj=def;
        if(this.yml.contains(path)){
            obj=this.getList(path, def);
        }
        if(this.force){
            obj=def;
        }
        this.set(path, obj);
        return obj;
    }
 
    public long getLong(final String path) {
        return this.yml.getLong(path);
    }
 
    public List<Long> getLongList(final String path) {
        return this.yml.getLongList(path);
    }
 
    public List<Long> getLongList(final String path, final List<Long> def) {
        List<Long> obj=def;
        if(this.yml.contains(path)){
            obj=this.getLongList(path);
        }
        if(this.force){
            obj=def;
        }
        this.set(path, obj);
        return obj;
    }
 
    public List<Map<?, ?>> getMapList(final String path) {
        return this.yml.getMapList(path);
    }
 
    public List<Map<?, ?>> getMapList(final String path, final List<Map<?, ?>> def) {
        List<Map<?, ?>> obj=def;
        if(this.yml.contains(path)){
            obj=this.getMapList(path);
        }
        if(this.force){
            obj=def;
        }
        this.set(path, obj);
        return obj;
    }
 
    public Object getObject(final String path) {
        return this.yml.get(path);
    }
 
    public OfflinePlayer getOfflinePlayer(final String path) {
        return this.yml.getOfflinePlayer(path);
    }
 
    public String getPath(final String path) {
        return this.getPath(path, path, "");
    }
 
    private String getPath(final String sPath, String sPath2, final String path) {
        String pathPrefix="";
        if(!path.isEmpty()){
            pathPrefix=path+".";
        }
        if(this.yml.isConfigurationSection(path)){
            final Set<String> key=this.yml.getConfigurationSection(path).getKeys(false);
            if(key.size()>0){
                final Object[] paths=key.toArray();
                for(final Object path2:paths){
                    final String tmp=this.getPath(sPath, sPath2, pathPrefix+path2.toString());
                    if(tmp.equalsIgnoreCase(sPath)){
                        return tmp;
                    }else{
                        sPath2=tmp;
                    }
                }
            }
        }else{
            return path;
        }
        if(path.isEmpty()){
            return sPath;
        }else{
            return sPath2;
        }
    }
 
    public List<Short> getShortList(final String path) {
        return this.yml.getShortList(path);
    }
 
    public List<Short> getShortList(final String path, final List<Short> def) {
        List<Short> obj=def;
        if(this.yml.contains(path)){
            obj=this.getShortList(path);
        }
        if(this.force){
            obj=def;
        }
        this.set(path, obj);
        return obj;
    }
 
    public String getString(final String path) {
        return this.yml.getString(path);
    }
 
    public List<String> getStringList(final String path) {
        return this.yml.getStringList(path);
    }
 
    public List<String> getStringList(final String path, final List<String> def) {
        List<String> obj=def;
        if(this.yml.contains(path)){
            obj=this.getStringList(path);
        }
        if(this.force){
            obj=def;
        }
        this.set(path, obj);
        return obj;
    }
 
    private boolean hasPath(final String sPath, final String path) {
        String pathPrefix="";
        if(!path.isEmpty()){
            pathPrefix=path+".";
        }
        if(this.yml.isConfigurationSection(path)){
            final Set<String> key=this.yml.getConfigurationSection(path).getKeys(false);
            if(key.size()>0){
                final Object[] paths=key.toArray();
                for(final Object path2:paths){
                    if(this.hasPath(sPath, pathPrefix+path2.toString())){ return true; }
                }
            }
        }else{
            return sPath.equalsIgnoreCase(path);
        }
        return false;
    }
 
    public void save() {
        try{
            this.yml.save(this.file);
        }catch (final IOException e){
            e.printStackTrace();
        }
    }
 
    public void set(final String path, final Object obj) {
        this.yml.set(path, obj);
    }
 
    public void setFileConfiguration(final File file, final FileConfiguration fileConfig) {
        this.file=file;
        this.yml=fileConfig;
    }
 
    public void setForceSave(final boolean force) {
        this.force=force;
    }
 
    public void setHeader(final String str) {
        this.yml.options().header(str);
    }
}