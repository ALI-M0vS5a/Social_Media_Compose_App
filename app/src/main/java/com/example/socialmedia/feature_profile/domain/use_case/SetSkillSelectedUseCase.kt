package com.example.socialmedia.feature_profile.domain.use_case

import com.example.socialmedia.R
import com.example.socialmedia.feature_profile.domain.model.Skill
import com.example.socialmedia.feature_profile.domain.util.ProfileConstants
import com.example.socialmedia.util.Resource
import com.example.socialmedia.util.UiText


class SetSkillSelectedUseCase {

    operator fun invoke(
        selectedSkills: List<Skill>,
        skillToToggle: Skill
    ): Resource<List<Skill>> {
        if (skillToToggle in selectedSkills) {
            return Resource.Success(selectedSkills - skillToToggle)
        }
        return if (selectedSkills.size >= ProfileConstants.MAX_SELECTED_SKILL_COUNT) {
            Resource.Error(
                UiText.StringResource(
                    R.string.error_max_skills_selected,
                    3
                )
            )
        } else {
            Resource.Success(selectedSkills + skillToToggle)
        }
    }
}