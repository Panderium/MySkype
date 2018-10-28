<template>
    <div id="app">
        <div class="tile is-ancestor">
            <div class="tile is-vertical">
                <div class="tile">
                    <div class="tile is-parent">
                        <article class="tile is-child is-3">
                            <conversations-list v-on:switch-conversation="name = $event"/>
                        </article>
                        <div class="tile is-parent">
                            <article class="tile is-child">
                                <conversation :name=name />
                            </article>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
    import ConversationList from '@/components/ConversationsList'
    import Conversation from '@/components/Conversation'
    import 'bulma/css/bulma.css'

    export default {
        name: "app",
        components: {
            'conversations-list': ConversationList,
            'conversation': Conversation
        },
        data() {
            return {
                name: null
            }
        }
    }
</script>