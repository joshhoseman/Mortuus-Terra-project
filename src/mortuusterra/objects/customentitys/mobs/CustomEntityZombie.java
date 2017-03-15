package mortuusterra.objects.customentitys.mobs;

import java.lang.reflect.Field;
import java.util.List;

import net.minecraft.server.v1_11_R1.EntityHuman;
import net.minecraft.server.v1_11_R1.EntityVillager;
import net.minecraft.server.v1_11_R1.EntityZombie;
import net.minecraft.server.v1_11_R1.PathfinderGoalFloat;
import net.minecraft.server.v1_11_R1.PathfinderGoalHurtByTarget;
import net.minecraft.server.v1_11_R1.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_11_R1.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_11_R1.PathfinderGoalMoveThroughVillage;
import net.minecraft.server.v1_11_R1.PathfinderGoalMoveTowardsRestriction;
import net.minecraft.server.v1_11_R1.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_11_R1.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_11_R1.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_11_R1.PathfinderGoalSelector;
import net.minecraft.server.v1_11_R1.World;

public class CustomEntityZombie extends EntityZombie {

	public CustomEntityZombie(World world) {
		super(world);
		
		List goalB = (List)getPrivateField("b", PathfinderGoalSelector.class, goalSelector); goalB.clear();
		List goalc = (List)getPrivateField("c", PathfinderGoalSelector.class, goalSelector); goalc.clear();
		List targetB = (List)getPrivateField("b", PathfinderGoalSelector.class, goalSelector); targetB.clear();
		List targetc = (List)getPrivateField("c", PathfinderGoalSelector.class, goalSelector); targetc.clear();
		
		
//		 this.goalSelector.a(0, new PathfinderGoalFloat(this));
//	     this.goalSelector.a(2, new PathfinderGoalMeleeAttack(this, 1.0D, false));
//	     this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, 1.0D, true));
//	     this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 1.0D));
//	     this.goalSelector.a(6, new PathfinderGoalMoveThroughVillage(this, 1.0D, false));
//	     this.goalSelector.a(7, new PathfinderGoalRandomStroll(this, 1.0D));
//	     this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
//	     this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
//	     this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, true));
//	     this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, 0, true, bA, null));
//	     this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityVillager.class, 0, false, bA, null));
	// This are its default goals.
	}

	public static Object getPrivateField(String fieldName, Class clazz, Object object) {
		Field field;
		Object o = null;
		
		try {
			field = clazz.getDeclaredField(fieldName);
			
			field.setAccessible(true);
			
			o = field.get(object);
		}
		catch(NoSuchFieldException e) {
			e.printStackTrace();
		}
		catch(IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return o;
	}
	
	

}
