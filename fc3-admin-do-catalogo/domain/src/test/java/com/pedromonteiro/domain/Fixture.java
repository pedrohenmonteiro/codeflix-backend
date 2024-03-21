package com.pedromonteiro.domain;


import com.pedromonteiro.domain.castmember.CastMember;
import com.pedromonteiro.domain.castmember.CastMemberType;
import com.pedromonteiro.domain.category.Category;

import net.datafaker.Faker;

public final class Fixture {
    
    private static final Faker FAKER = new Faker();

    public static String name() {
        return FAKER.name().fullName();
    }


       public static final class Categories {

        private static final Category AULAS =
                Category.newCategory("Aulas", "Some description", true);

        private static final Category LIVES =
                Category.newCategory("Lives", "Some description", true);

        public static Category aulas() {
            return AULAS.clone();
        }

        public static Category lives() {
            return LIVES.clone();
        }
    }

    public static final class CastMembers {

        private static final CastMember WESLEY =
                CastMember.newMember("Wesley FullCycle", CastMemberType.ACTOR);

        private static final CastMember GABRIEL =
                CastMember.newMember("Gabriel FullCycle", CastMemberType.ACTOR);

        public static CastMemberType type() {
            return FAKER.options().option(CastMemberType.values());
        }

        public static CastMember wesley() {
            return CastMember.with(WESLEY);
        }

        public static CastMember gabriel() {
            return CastMember.with(GABRIEL);
        }
    }
}
