package net.adventurez.entity.goal;

import net.adventurez.entity.OrkEntity;
import net.minecraft.entity.ai.TargetFinder;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

public class WanderAroundVeryFarGoal extends WanderAroundGoal {
    protected final float probability;

    public WanderAroundVeryFarGoal(PathAwareEntity mob, double speed, float probability) {
        super(mob, speed);
        this.probability = probability;
    }

    @Nullable
    @Override
    protected Vec3d getWanderTarget() {
        if (this.mob.isInsideWaterOrBubbleColumn()) {
            Vec3d vec3d = TargetFinder.findGroundTarget(this.mob, 15, 7);
            return vec3d == null ? super.getWanderTarget() : vec3d;
        } else {
            return this.mob.getRandom().nextFloat() >= this.probability && this.isBigOrk(mob)
                    ? TargetFinder.findGroundTarget(this.mob, 16, 5)
                    : super.getWanderTarget();
        }
    }

    private boolean isBigOrk(PathAwareEntity pathAwareEntity) {
        if (pathAwareEntity instanceof OrkEntity && ((OrkEntity) pathAwareEntity).getSize() == 3) {
            return true;
        } else
            return false;
    }
}